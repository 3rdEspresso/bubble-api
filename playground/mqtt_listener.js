const mqtt = require('mqtt');
const mqttClient = mqtt.connect({
    host: 'localhost',
    port: 1883,
    username: 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiMDIxYTY5MC01OWJlLTQwNDUtYjlhMC1mYjEwNzllYzcyZTciLCJleHAiOjE2ODg4Nzk0ODksImlhdCI6MTY4ODI3NDY4OX0.Xnvb9P3zq-j6nm8xz4GREubK67lYBPi0ZZppaos-CrU',
    password: '',
    clean: true,
    clientId: 'bubble_test_listener',
    // Cannot have client with same clientId, deployed server already uses `mqtt_presence`
});

mqttClient.on('connect', function () {
    console.log('connected');
    mqttClient.subscribe(['bubble/34eef945-009e-4bee-ba61-3d54cacc799d', 'bubble/34eef945-009e-4bee-ba61-3d54caac799d'], function (err) {
        if (!err) {
            console.log('SUBSCRIBED!');
            // mqttClient.publish('bubble/34eef945-009e-4bee-ba61-3d54cacc799d', '{"lmfao": "test true"}', { qos: 0 });
            // mqttClient.publish('bubble/34eef945-009e-4bee-ba61-3d54caac799d', '{"lmfao": "test false"}');
        } else {
            console.log(err);
        }
    });
});

mqttClient.on('message', function (topic, message) {
    // message is Buffer
    console.log('RECEIVED', message.toString());
    // mqttClient.end();
})

// error
mqttClient.on('error', function (error) {
    console.log(error);
    mqttClient.end();
})