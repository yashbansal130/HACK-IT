const express = require('express');
const { getDatabase, set, ref, child, get } = require('firebase/database');
const { database } = require('../firebase');
const router = express.Router();

//Get all items
router.get('/', async (req, res) => {
  const dbRef = ref(getDatabase());
  try {
    const snapshot = await get(child(dbRef, `items/`));
    if (snapshot.exists()) {
      res.send(snapshot.val());
    } else {
      res.send({ msg: 'No data available' });
    }
  } catch (error) {
    res.status(400).send('Server Error');
  }
});

module.exports = router;
