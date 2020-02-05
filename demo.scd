
~freq_to_find = [];
89.do({arg do; ~freq_to_find= ~freq_to_find.add((do+26).midicps;); });


//this will take 3 minutes to sweep
// If tuning fails try incresing sweep time   
~min = 3;
~q = Joevolt.new(60*min,0,0,~freq_to_find);
~q.listen;
~q.sweep;
// wait for a bit ....

~q.build_tree;
//~q.out_tree;


SynthDef(\cv_synth, {arg dur = 1,freq=0.1,out;
    Line.kr(0,0,dur,0,0,2);
    Out.ar(out,K2A.ar(freq));
}).add;





(
~dur =0.3;

Pbind(\out ,0,\type, \freqtocv,\instrument, \cv_synth,  \dur, ~dur,\tree ,~q.out_tree;,\degree,Pseq((0..12),inf)).play;

)
