# Utazási iroda

Az applikáció célja hogy összegyüjtsük az utazási irodákat és azoknak az utazásait.

## Az alkalmazás főbb egységei
### Travel Agency:
Az utazási ügynőkségekről a következő információkat tároljuk el.: Név, utazások és lokáció.
### Location:
A lokációrúl a következő információkat raktározhatjuk.: ISO, ország, város, utca és a száma.
### Destination:
Utazásnál a következő indormációkat jegyezhetjük fel.: Cím, leírás, napi ára, pontos lokáció, és az utazási ügynökség.

## Endpointok
### Destination
Utazást elmenthetünk az adatbázisba, módosíthatjuk az összes adatát, törölhetjük, kikereshetjük az összeset vagy csak egy utazást is.
### Location
Lokációnál kikereshetünk egy bizonyos lokációt Id alapján az adatbázisból vagy akár az összeset, modósíthatjuk az adatait és létre is hozhatunk egy újjat.
### Travel Agency
Utazási irodánál létrehozhatunk új utazási irodát kikereshetjük id alapján vagy módosíthatjuk.
