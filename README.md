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

testing:
- cdi with GraalVM
- cache
- reactive REST (https://quarkus.io/blog/resteasy-reactive-smart-dispatch/) without overhead
