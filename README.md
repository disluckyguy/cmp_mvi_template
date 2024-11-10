# Compose Multiplatform MVI Template

This is a Compose Multiplatform template for my own projects, feel free to use it or take inspiration from it.

## Structure
The struction is based on the MVI architecture, every feature(in this template a counter screen as an example) is put in its own folder with:

- a "data" folder that contains functions to access and obtain raw data(not needed in the template example so it's not put)
- a "domain" folder, which contains files for state classes, event classes, and a ModelView that utilizes the state and modifies it using the events
- a "view" folder that contains the UI widgets

The structure is inspired by BloC architecture which is popular when developing with Flutter
