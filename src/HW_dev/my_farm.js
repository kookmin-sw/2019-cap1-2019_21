const awsIot =require('aws-iot-device-sdk');
//const  = require('/mnt/c/Users/wjdgm/my_farm/connect_device_package');
var five =require('johnny-five');
var events =require('events');
var emitter = new events.EventEmitter();
const device = awsIot.device({
    keyPath: 'my_farm.private.key',
    certPath: 'my_farm.cert.pem',
    caPath: 'root-CA.crt',
    clientId: 'sdk-nodejs-4bdbf262-9d55-4445-abd3-d12d47767f3d\n',
    region: 'us-west-2',
    host:'a28jgqlr4nhpzv-ats.iot.us-west-2.amazonaws.com'
});

var contents ="Start....!!!";

device.on('connect',function () {
    console.log("connect");
   // device.publish('farm_control_policy', JOSN.stringify({test_date:"world..."}));
    console.log("Message Sent ...");
});
device.on('message',function (topic,payload) {
    console.log('connect',topic,payload.toString());
})

var board =new five.Board();
board.on('ready', function () {
    var LED = new five.Led({
        pin:5
    })
    emitter.on('blink',function (ms) {
        LED.blink(ms);
    });
    emitter.on('ledoff',function () {
        LED.off();

    })
    console.log("UNO is connected!");
})
