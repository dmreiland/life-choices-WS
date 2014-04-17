/**
 * -----------------------
 * Start Static Web Server
 * -----------------------
 */
var connect = require('connect');

console.log("Starting ...");
connect.createServer(connect.static(__dirname)).listen(8888);
console.log("Started static web site on port: " + 8888);



var sys = require('sys');
var exec = require('child_process').exec;
var express = require('express');
var app = express();

app.get('/pull-restart', function (req, res) {

    exec("/home/ubuntu/life-choices-WS/pull-restart.sh",

        function (error, stdout, stderr) {
            console.log('stdout: ' + stdout);
            console.log('stderr: ' + stderr);

            if (error !== null) {
                console.log('exec error: ' + error);
                var json = {
                    "status":"error",
                    "message": "exec error:" + error,
                    "time": new Date()
                };

                res.send(json);
            } else {

                var json = {
                    "status":"success",
                    "message": "good job",
                    "time": new Date()
                };

                res.send(json);
            }
        });


});

console.log("Started web services on port: " + 9999);
app.listen(process.env.PORT || 9999);

