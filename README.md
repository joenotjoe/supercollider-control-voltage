There is a new Quark for people who want to play with CV And Suppercollider go take a look.
https://github.com/capital-G/EuroCollider

-----------------------------------------------------------------------------------------------------



# supercollider-control-voltage
supercollider control voltage class lets you tune eurorck...   I hope others find it useful.

Now you can install as a Quark

Quarks.install("https://github.com/joenotjoe/supercollider-control-voltage");

todo 
1. better/real help file
2. Rename joevolt seems a bit protenches. Nameing is hard.
3. the code works but is slopy so clean it up. (install quarks dependencies kdtree ect)
4. Would like to for pitch detection  to use the Fluid Decomposition Lib Ugens or Tartini. Will probibly wait till the Fluid Decomposition Lib is release it as a Quark. I have used Tartini in the past but wanted everyting to work out of the box on a clean install of SC with out installing other plugins.
FluidDecomposition Library  http://www.flucoma.org/ 
http://doc.sccode.org/Classes/Tartini.html
5. It should be posible to make a better tuneing algorithm. Cuerntly it just scans up in volts looking for the freq to match your scale. I feel like most people are in the 1oct/volt relm and useing something like Expert sleeper.  
