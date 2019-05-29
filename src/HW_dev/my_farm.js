var awsIot =require('aws-iot-device-sdk');
var five = require("johnny-five");
var schedule = require('node-schedule');
var exec_photo = require('child_process').exec;
require("date-utils");
var newDate=new Date();
var time = newDate.toFormat("YYYY-MM-DD");
var time1 = newDate.toFormat("HH24:MI:SS");
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
var soildata;
var temp1=30;
var soilmo=200;
device.on('connect',function() {
    console.log(time);

    console.log("connect");
    device.subscribe('my_farm');
});

device
    .on('message', function(topic, payload) {
        var app = payload.toString().split(",");
        temp1=app[0];
        soilmo=app[1];
    });

board.on("ready",function(){
    var soil = new five.Sensor({
        pin:3,
        freq:10000
    });
    var multi = new five.Multi({
        controller: "BME280",
        freq:10000
    });

    var motors = new five.Motors([
        {pins:{dir:4,pwm:5},invertPWM:true},
        {pins:{dir:7,pwm:6},invertPWM:true}
    ]);


    soil.on("data",function () {
        console.log("soil data : "+this.value)
        soildata = this.value;
        if (this.value< soilmo )
            console.log("water motor")
        motors[0].start(255);
        board.wait(5000,function () {
            motors.stop()
        })
    });
    var relay = new five.Relay(10);


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
        device.publish('my/smartpot2', JSON.stringify({"row": time, "pos":time1,"temp":this.thermometer.celsius,"soil":String(soildata),"humi":this.hygrometer.relativeHumidity}));
        if (Number(this.thermometer.celsius)>temp1){
            console.log('fan ahead!');
            motors.start(255);
            relay.on();
        }else{
            relay.off();
            motors.stop();
        }

    });
});





