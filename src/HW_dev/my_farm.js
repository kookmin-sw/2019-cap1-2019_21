var awsIot =require('aws-iot-device-sdk');
var five = require("johnny-five");
var events =require('events');
var eventEmitter=new events.EventEmitter();
var board = new five.Board();
var device = awsIot.device({
    keyPath: 'my_farm.private.key',
    certPath: 'my_farm.cert.pem',
    caPath: 'root-CA.crt',
    clientId: 'sdk-nodejs-4bdbf262-9d55-4445-abd3-d12d47767f3d\n',
    region: 'us-west-2',
    host:'a28jgqlr4nhpzv-ats.iot.us-west-2.amazonaws.com'
});

var timestamp = Math.floor(+ new Date()/60000);
device.on('connect',function() {
    console.log(timestamp)
    console.log("connect");
    device.subscribe('my/smartpot');
    //device.publish('my/smartpot',JSON.stringify({"row":this.barometer.pressure,"pos":3}));
    console.log('Set shadow value.');
});

device.on('connect',function(topic,payload) {
    console.log('CONNECT')
});
board.on("ready",function(){
    var multi = new five.Multi({
        controller: "BME280",
        freq:10000
    });
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
        device.publish('my/smartpot', JSON.stringify({"row": this.hygrometer.relativeHumidity, "pos": timestamp}));

    });
});

board.on('ready',function () {
    var motors = new five.Motors([
        {pins:{dir:4,pwm:5},invertPWM:true},
        {pins:{dir:7,pwm:6},invertPWM:true}
    ]);

    board.repl.inject({
        motors:motors
    });
    console.log('fan ahead!');
    motors[1].start(255);
    board.wait(5000,function () {
        motors.stop();
    });

    console.log("water motor")
    motors[0].start(255);
    board.wait(5000,function () {
        motors.stop()
    })

    console.log('Done auto-driving! Use')
});




