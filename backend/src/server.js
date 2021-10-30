const express = require('express');
const dotenv = require('dotenv');

//env variables
dotenv.config();

const app = express();

//routes
app.get('/', (req, res) => {
  res.send(
  {"shoes":[
      {"id":"AVpfHrJ6ilAPnD_xVXOI",
      "name":"Josmo",
      "imageurl":"https://i5.walmartimages.com/asr/13ac3d61-003c-4a30-94db-7d4e7e94fd3b_1.157f2cdc2551cda33f1191df19dd94ff.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpfHsWP1cnluZ0-eVZ7",
      "name":"SERVUS BY HONEYWELL",
      "imageurl":"https://i5.walmartimages.com/asr/22cf3f4b-d28b-4a72-a2bb-15120e0259d5_1.e496dff47c69e9e7530f24e9a7e609d6.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpfAwehilAPnD_xS_eB",
      "name":"NATIVE",
      "imageurl":"https://i5.walmartimages.com/asr/bfd75ded-1ab5-4726-bb90-c8e9cf8a3b59_1.98484275b5214e512ad93dd99df44a2b.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpfFCHeilAPnD_xUeS0",
      "name":"MAUI AND SONS",
      "imageurl":"https://i5.walmartimages.com/asr/0ca84b98-3c3b-4dcd-a34e-80f8092cab05_1.d2ec9505fa96754a621e7dc8702390ce.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpfDweJilAPnD_xUBrV",
      "name":"Filament",
      "imageurl":"https://i5.walmartimages.com/asr/1be177f9-e6ce-48c0-b16f-6def283aedb9_1.32d3d77d65ccc3179dea8de024d880f6.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpfB-8RLJeJML4303Xb",
      "name":"AMERICAN FIGHTER",
      "imageurl":"https://i5.walmartimages.com/asr/007911aa-3676-4a8b-a543-604d2a18b0b2_1.32eb854af877342900b2e72141100f54.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF,https://i5.walmartimages.com/asr/02ff5daa-135b-4369-b12d-a710e4ff501c_1.9cb06a064cde6b20fde3e183828aae01.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpfJEaqLJeJML433Rz6",
      "name":"PUMA",
      "imageurl":"https://i5.walmartimages.com/asr/6c3f42c2-2fa0-46cc-9851-6062743eda03_1.fa694d632632709897d52fdca4991cdb.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpfIBrDilAPnD_xVevt",
      "name":"Maui Jim",
      "imageurl":"https://i5.walmartimages.com/asr/8d3f80fe-de31-47dc-8c6e-70863f438fde_1.d7a308de1443a34d83ae1b52b8af44ed.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpe7Bp0ilAPnD_xQ8Sh",
      "name":"VENUM",
      "imageurl":"https://i5.walmartimages.com/asr/fa9a77ab-bbae-4942-924e-63f3748dabf0_1.609f026020c0dd6c1dcb383e4622411e.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpfJHn81cnluZ0-e0n7",
      "name":"Azzuro",
      "imageurl":"https://i5.walmartimages.com/asr/b007610a-928e-4a7a-b20c-2f7be89c9b76_1.de40f2ca2b6e2167394d6faa458e48a5.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpfEsCZ1cnluZ0-dUFR",
      "name":"adidas",
      "imageurl":"https://i5.walmartimages.com/asr/fe2e2d2a-8958-461d-bdb9-aab2332c7d84_1.54a3752bdb7695f62d6a8979a3cfbf45.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpfJJUsilAPnD_xV3O7",
      "name":"Reebok",
      "imageurl":"https://i5.walmartimages.com/asr/07630be8-713a-46df-8abf-078d7a23c432_1.3645bec32633e76a77bb135716626ea1.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpfElTsLJeJML431wPt",
      "name":"Robert Wayne",
      "imageurl":"https://i5.walmartimages.com/asr/8ddf4705-bcc2-4fd0-a56d-e3691cb00bb3_1.1e168531cbdd1c085d903cd4f3acf005.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpfJEMqLJeJML433RvY",
      "name":"Puma",
      "imageurl":"https://i5.walmartimages.com/asr/6c1fa8f6-5389-43f3-bd3a-f6f9ef3fc09c_1.3dd917885eb4712280a7633bdad796a1.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
      ,{"id":"AVpfI-6OLJeJML433P81",
      "name":"Nike",
      "imageurl":"https://i5.walmartimages.com/asr/70d05add-05d7-48c2-9cfc-45737db2e3e3_1.1df6a27529da1a5349791955855d651d.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"}
  ]
  });
});

//server
app.listen(process.env.PORT, () => {
  console.log(`Server Up and Running 🚀🚀 on PORT: ${process.env.PORT}`);
});
