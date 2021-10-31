// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getDatabase } from "firebase/database";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyAZmmVCr0umVi-VC_vqcqBHefunhJqhU-U",
  authDomain: "hack-it-f1847.firebaseapp.com",
  databaseURL: "https://hack-it-f1847-default-rtdb.firebaseio.com",
  projectId: "hack-it-f1847",
  storageBucket: "hack-it-f1847.appspot.com",
  messagingSenderId: "510903415721",
  appId: "1:510903415721:web:ad465179680f8d9aabcab8",
  measurementId: "G-GRVQ8TNY24"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
