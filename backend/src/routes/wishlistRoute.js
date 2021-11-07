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
  const memberId = req.body.member1;
  const groupId = uuidv4();
  try {
    // get old userinfo
    const snapshot = await get(child(dbRef, `users/${userId}`));
    const snapshot1 = await get(child(dbRef, `users/${memberId}`));
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
      //add member to wishList
      if (snapshot1.exists()) {
        const oldMemberName = snapshot1.val().name;
        const oldMemberEmail = snapshot1.val().email;
        const oldMemberId = snapshot1.val().id;
        const oldMemberWishlist = snapshot1.val().wishlist;
        set(ref(db, 'users/' + memberId), {
          name: oldMemberName,
          email: oldMemberEmail,
          id: memberId,
          wishlist: oldMemberWishlist
            ? [...oldMemberWishlist, { id: groupId, groupname }]
            : [{ id: groupId, groupname }],
        });
      } else {
        return res.send({ msg: 'failure' });
      }
      //add wishlist to collection
      set(ref(db, 'wishlist/' + groupId), {
        id: groupId,
        groupname: groupname,
        selectedItems: [],
      });
      res.send({ msg: 'Success' });
    } else {
      res.send({ msg: 'failure' });
    }
  } catch (error) {
    res.status(400).send({ msg: 'failure' });
  }
});

//POST add item to wishlist
router.post('/additem', async (req, res) => {
  const db = getDatabase();
  const dbRef = ref(getDatabase());
  const userID = req.body.userID;
  const itemID = req.body.itemID;
  const groupID = req.body.groupID;
  try {
    const snapshot = await get(child(dbRef, `wishlist/${groupID}`));
    if (snapshot.exists()) {
      const oldGroupName = snapshot.val().groupname;
      const oldGroupId = snapshot.val().id;
      const oldSelectedItems = snapshot.val().selectedItems
        ? snapshot.val().selectedItems
        : [];
      set(ref(db, 'wishlist/' + groupID), {
        groupname: oldGroupName,
        id: oldGroupId,
        selectedItems: oldSelectedItems
          ? [...oldSelectedItems, { itemId: itemID, addedById: userID }]
          : oldSelectedItems,
      });
      res.send({ msg: 'Success' });
    } else {
      res.send({ msg: 'failure' });
    }
  } catch (error) {
    console.log(error.message);
    res.status(400).send({ msg: 'failure' });
  }
});
module.exports = router;
