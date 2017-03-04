# Testausdokumentti
- Testikattavuus oli pitkään hyvin vähäistä, sillä en osannut kirjoittaa testejä käyttöliittymälle, josta aloitin projektin. Suurimman osan testeistä tein aivan lopuksi.
- Mutaatiokattavuus jäi hyvin vähälle, sillä tässä pelissä käyttäjä ei voi antaa "virheellistä" inputtia, muuten kuin painamalla vääriä näppäimiä, jolloin ei tapahdu mitään. Tätä varten on käyttöohje.
- Automaattisesta testauksesta jäi testaamatta mm. "kameran" liikuttaminen, pelaajan kohtaaminen blokatun tiilin kanssa (koordinaattien päivittyminen), vihollisen kuollessa ilmestyvä räjähdys ja voittoruudun ilmestyminen maalikoordinaatin saavutettaessa.
- Kaikki edellä mainitut testasin käsin pelaamalla itse peliä pyrkimällä tarkistamaan kaikki eri rajatapaukset kyseisillä tapauksilla.
- Pelissä oli muutama outo bugi, mutta tietääkseni sain ne kaikki korjattua ennen viimeistä pushia ja jar-tiedoston generointia.
