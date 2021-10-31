const express = require('express');
const dotenv = require('dotenv');
const { getDatabase, set, ref, child, get } = require('firebase/database');
const { database } = require('./firebase');

const itemRoute = require('./routes/itemRoute');
//env variables
dotenv.config();

const app = express();

//routes
app.get('/', (req, res) => {
  res.send('Namaste!');
});
app.use('/items', itemRoute);
//server
app.listen(process.env.PORT, () => {
  console.log(`Server Up and Running 🚀🚀 on PORT: ${process.env.PORT}`);
});
