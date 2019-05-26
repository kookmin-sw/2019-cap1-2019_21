var awsIot =require('aws-iot-device-sdk');
var five = require("johnny-five");
var schedule = require('node-schedule');
var exec_photo = require('child_process').exec;
require("date-utils");
var newDate=new Date();
var time = newDate.toFormat("YYYY-MM-DD HH24:MI:SS");
var board = new five.Board();
var device = awsIot.device({
    keyPath: 'my_farm.private.key',
    certPath: 'my_farm.cert.pem',
    caPath: 'root-CA.crt',
    clientId: 'sdk-nodejs-4bdbf262-9d55-4445-abd3-d12d47767f3d\n',
    region: 'us-west-2',
    host:'a28jgqlr4nhpzv-ats.iot.us-west-2.amazonaws.com'
});

var rule = new schedule.RecurrenceRule();
rule.second = 10;

var j = schedule.scheduleJob(rule, function(){
    var photo_path = '/home/pi/camera/'+ time +'.jpg';
    var cmd_photo  = 'raspistill -o ' + photo_path + ' -w 640 -h 480';

    exec_photo(cmd_photo,function(error,stdout,stderr){
        console.log('Photo saved : ' , photo_path);

        if (error) {
            console.error(`exec error: ${error}`);
            return;
        }
        console.log('camera update');
    });

});


device.on('connect',function() {
    console.log(time);

    console.log("connect");
    device.subscribe('my/smartpot');
    //device.publish('my/smartpot',JSON.stringify({"row":this.barometer.pressure,"pos":time}));
    console.log('Set shadow value.');
});

board.on("ready",function(){
    var multi = new five.Multi({
        controller: "BME280",
        freq:10000
    });
    var motors = new five.Motors([
        {pins:{dir:4,pwm:5},invertPWM:true},
        {pins:{dir:7,pwm:6},invertPWM:true}
    ]);
    var soil = new five.Sensor({
        pin:3,
        freq:10000
    });

    soil.on("change",function () {
        console.log(this.value)
        if (this.value< 100 || 600>this.value)
            console.log("water motor")
        motors[0].start(255);
        board.wait(5000,function () {
            motors.stop()
        })
    })

    multi.on("data", function(){
        console.log("celsius     : ",this.thermometer.celsius);
        console.log("fahrenheit  : ",this.thermometer.fahrenheit);
        console.log("kelvin      : ",this.thermometer.kelvin);
        console.log("---------------------------");

        console.log("Barometer");
        console.log("pressure    : ",this.barometer.pressure);
        console.log("---------------------------");

        console.log("Hygrometer");
        console.log("humidity    : ",this.hygrometer.relativeHumidity);
        console.log("---------------------------");

        console.log("Altimeter");
        console.log("feet        : ",this.altimeter.feet);
        console.log("meters      : ",this.altimeter.meters);
        console.log("---------------------------");
        device.publish('my/smartpot', JSON.stringify({"row": time, "pos": Number(this.thermometer.celsius)}));
        if (Number(this.thermometer.celsius)>20){
            console.log('fan ahead!');
            board.wait(10000,function(){
                motors.start(255);
            });
        }

    });
});





