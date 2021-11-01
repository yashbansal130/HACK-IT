const express = require('express');
var bodyParser = require('body-parser');
const dotenv = require('dotenv');
const { getDatabase, set, ref, child, get } = require('firebase/database');
const { database } = require('./firebase');

const itemRoute = require('./routes/itemRoute');
const userRoute = require('./routes/userRoute');
//env variables
dotenv.config();

const app = express();
// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }));

// parse application/json
app.use(bodyParser.json());

//routes
app.get('/', (req, res) => {
  res.send('Namaste!');
});
app.use('/items', itemRoute);
app.use('/users', userRoute);
//server
app.listen(process.env.PORT, () => {
  console.log(`Server Up and Running 🚀🚀 on PORT: ${process.env.PORT}`);
});
