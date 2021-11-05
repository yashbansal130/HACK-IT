const express = require('express');
const { getDatabase, set, ref, child, get } = require('firebase/database');
const router = express.Router();

//Get user info by ID
router.get('/:id', async (req, res) => {
  const db = getDatabase();
  const userId = req.params.id;
  const dbRef = ref(getDatabase());
  try {
    const snapshot = await get(child(dbRef, `users/${userId}`));
    if (snapshot.exists()) {
      res.send(snapshot.val());
    } else {
      res.send({ msg: 'No data available' });
    }
  } catch (error) {
    res.status(400).send('Server Error');
  }
});
// GET all user info
router.get('/', async (req, res) => {
  const db = getDatabase();
  const dbRef = ref(getDatabase());
  try {
    const snapshot = await get(child(dbRef, `users/`));
    if (snapshot.exists()) {
      res.send(snapshot.val());
    }
  } catch (error) {
    res.status(400).send({ msg: 'failure' });
  }
});
//POST to add user to database
router.post('/', async (req, res) => {
  const db = getDatabase();
  const dbRef = ref(getDatabase());
  const userId = req.body.id;
  const name = req.body.name;
  const email = req.body.email;
  try {
    const snapshot = await get(child(dbRef, `users/${userId}`));
    if (snapshot.exists()) {
      console.log('pehle se hai');
      res.send({ msg: 'Success' });
    } else {
      set(ref(db, 'users/' + userId), {
        name: name,
        email: email,
        id: userId,
        wishlist: [],
      });
      res.send({ msg: 'Success' });
    }
  } catch (error) {
    res.status(400).send({ msg: 'failure' });
    console.log(error.message);
  }
});
module.exports = router;
