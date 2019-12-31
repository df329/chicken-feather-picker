# OSRS Chicken feather picker

OSRS bot for picking up chicken feathers at Lumbridge.

## Getting started

Steps are for IntelliJ IDEA 2019.x and assumption is JRE 1.8 is installed (as default).

### Cloning

```bash
git clone https://github.com/df329/chicken-feather-picker.git
```

### RSBot location

You will need to modify the location to the `RSBot-<version>.jar` in:

* `.idea/workspace.xml`
* `.idea/libraries/RSBot_8_0_5.xml`

Also change the version accordingly to the above files and also in the `src/chicken-feather-picker.iml`.

## Future

Feature wise it will remain the same, additional features will be spun of into their own scripts.

* Some randomness for anti-ban
  * Camera rotation
  * Mouse movements
  * Walking/idle actions
* Drop non-feather items - mis-clicks etc.
