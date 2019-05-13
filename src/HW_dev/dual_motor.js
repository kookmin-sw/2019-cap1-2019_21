var five = require('johnny-five'),
    board = new five.Board();

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