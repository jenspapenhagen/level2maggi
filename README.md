# This is a MircoService for Converting the Level of the Saar into bottle count of "Maggi Würze (250g)"

#### (Measuring Point St. Arnual)

This Project is based on a default Quarkus Project.
And use the Rest-APi from https://www.pegelonline.wsv.de/

## Main Goal:
- playing around with Quarkus Extensions
- non-blocking service
- fun

for scale:\
![for scale of a Maggi](https://github.com/jenspapenhagen/level2maggi/blob/master/images/maggifalschegroesse.jpg?raw=true)


```
wget --header="Content-Type: text/json" http://localhost:8080/177
```
For getting this Response
```json
{
  "count":15,
  "timeStamp":"2022-04-08T20:00:00+02:00",
  "unit":"An Maggi Würze 250g Bottle with 177 mm height."
}
```

There are this size of bottles available:
- **Maggi Würze 125g** (with 100mm)
- **Maggi Würze 250g** (with 177mm)
- **Maggi Würze 1000g** (with 270mm)

Testing:
- cdi with GraalVM
- cache
- reactive REST (https://quarkus.io/blog/resteasy-reactive-smart-dispatch/) without overhead
