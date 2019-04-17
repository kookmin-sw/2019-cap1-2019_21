const awsIot =require('aws-iot-device-sdk');
var five = require("johnny-five")
const device = awsIot.device({
    keyPath: 'my_farm.private.key',
    certPath: 'my_farm.cert.pem',
    caPath: 'root-CA.crt',
    clientId: 'sdk-nodejs-4bdbf262-9d55-4445-abd3-d12d47767f3d\n',
    region: 'us-west-2',
    host:'a28jgqlr4nhpzv-ats.iot.us-west-2.amazonaws.com'
});
device.on('connect',function () {
    console.log("connect");
    // device.publish('farm_control_policy', JOSN.stringify({test_date:"world..."}));
    console.log("Message Sent ...");
});
device.on('message',function (topic,payload) {
    console.log('connect',topic,payload.toString());
})

board.on("ready",function(){
    var multi = new five.Multi({
        controller: "BME280"
    });
setTimeout(()=>{
},100)
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
    });
});
