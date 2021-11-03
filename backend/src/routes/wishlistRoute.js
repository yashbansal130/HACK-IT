const express = require('express');
const { getDatabase, set, ref, child, get } = require('firebase/database');
const { v4: uuidv4 } = require('uuid');
const { database } = require('../firebase');
const router = express.Router();

//GET info of a wishlist
router.get('/:id', async (req, res) => {
  const db = getDatabase();
  const dbRef = ref(getDatabase());
  const wishlistId = req.params.id;
  try {
    const snapshot = await get(child(dbRef, `wishlist/${wishlistId}`));
    if (snapshot.exists()) {
      res.send({ msg: snapshot.val() });
    } else {
      res.send({ msg: 'failure' });
    }
  } catch (error) {
    res.status(400).send({ msg: 'failure' });
  }
});

//GET to get wishlist of user
router.get('/', async (req, res) => {
  const db = getDatabase();
  const dbRef = ref(getDatabase());
  const userId = req.body.id;
  try {
    const snapshot = await get(child(dbRef, `users/${userId}`));
    if (snapshot.exists()) {
      const wishlistArray = snapshot.val().wishlist;
      if (wishlistArray.length > 0) {
        res.send(wishlistArray);
      } else {
        res.send([]);
      }
    } else {
      res.send([]);
    }
  } catch (error) {
    res.status(400).send({ msg: 'failure' });
  }
});

//POST add wishlist to user
router.post('/', async (req, res) => {
  const db = getDatabase();
  const dbRef = ref(getDatabase());
  const userId = req.body.id;
  const groupname = req.body.groupname;
  const groupId = uuidv4();
  try {
    // get old userinfo
    const snapshot = await get(child(dbRef, `users/${userId}`));
    if (snapshot.exists()) {
      const oldUserName = snapshot.val().name;
      const oldUserEmail = snapshot.val().email;
      const oldUserId = snapshot.val().id;
      const oldUserWishlist = snapshot.val().wishlist;
      //add wishlist to user
      set(ref(db, 'users/' + userId), {
        name: oldUserName,
        email: oldUserEmail,
        id: userId,
        wishlist: oldUserWishlist
          ? [...oldUserWishlist, { id: groupId, groupname }]
          : [{ id: groupId, groupname }],
      });
      //add wishlist to collection
      set(ref(db, 'wishlist/' + groupId), {
        id: groupId,
        groupname: groupname,
      });
      res.send({ msg: 'Success' });
    } else {
      res.send({ msg: 'failure' });
    }
  } catch (error) {
    res.status(400).send({ msg: 'failure' });
  }
});

module.exports = router;
