##Aihemäärittely
**Aihe**: Tasohyppely. Toteutetaan peli, jossa käyttäjä ohjaa  hahmoa läpi kenttien, joissa esiintyy vihollisia tai erilaisia esteitä. Hahmo pystyy lyömällä tuhoamaan vihollisia.

**Käyttäjät**: Pelaaja

**Kaikkien käyttäjien toiminnot**: 
- Hahmon liikuttaminen vasemmalle/oikealle
- Hahmolla hyppääminen
- Hahmolla hyökkääminen


**Luokkakaavio**:

![Luokkakaavio](https://yuml.me/4ae4dd4b)

**Sekvenssikaaviot**:

![Aloita peli](http://i.imgur.com/pKYuGfv.png)
![Lopeta peli](http://i.imgur.com/7xIzI6R.png)

**Rakennekuvaus**: PeliPaneeli luo peli-ikkunan sisällön ja aloittaa threadin kutsuen pelitilamanageria. Pelitilamanager sisältää listan eri pelitiloista (menu, taso 1, taso 2, taso 3...) ja metodit joilla se voi vaihtaa tämänhetkistä pelitilaa toiseksi, tai kutsua tämänhetkistä pelitilaa päivittämään itsensä tai piirtämään itsensä ruudulle. Pelitila luokassa puolestaan kutsutaan entity paketin ja tilemap paketin luokkia joita pyydetään lataamaan itsensä ennalta määritellyistä tiedostopoluista, jonka jälkeen niitä kutsutaan asettamaan itsensä tiettyihin koordinaatteihin, ja lopulta piirtämään itsensä ruudulle. Update metodit päivittävät objektejen spritejä, tarkistavat niiden törmäyksiä ja asettavat seuraavat koordinaatit joihin niitä piirtää. Tilemapin setPosition metodia kutsutaan aina pelitilan updaten yhteydessä, ja se vastaa "kameran" liikuttamisesta piirtämällä aina vain ne tiilit jotka näkyvät ruudulla katsomalla pelaajan koordinaatteja.
