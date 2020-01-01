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
* Attack mode configuration
* Door/gate issues
  * Difficult, probably some localization needed

## Limitations

A few limitations of this script which probably won't be addressed:

* Attack modes - if you're a pure haha XD
* Guarding against mis-clicks entering combat
* It won't go find a chicken feather pit sorry
* ~~Optimizations for certain locations~~
