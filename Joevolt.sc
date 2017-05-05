

Joevolt {

	 var  <> dur,<> inchan ,<> outchan ,<> freq_to_find  , <> tree_to_out ,<> ofset,<> difamount , <> cv_to_use, <> uuid, <> tree ,<>freqto_cv_volt, ofun;


*new {  arg dur,inchan ,outchan,freq_to_find;

// adds event to find the closist match to freq
Event.addEventType(\freqtocv, { |server|
    ~freq =  ~tree.nearest([~freq.value,0]).at(1);
    ~type = \note;
    currentEnvironment.play;
});

//SynthDef play value out to dc cuppled sound card and pass freq to lang side ve osc
SynthDef(\joevolt;, {arg output_range = #[-1,1],dur ,freq ,out,in,idnum;
    var freqb,freqout,hasfreq;
    freqb = Line.ar(output_range.at(0),output_range.at(1),dur,1,0,2);

    #hasfreq = Pitch.kr(SoundIn.ar([in]),initFreq: 30000, ampThreshold: 0.02, median: 7);
 Out.ar(out,K2A.ar(freqb));
	SendReply.ar(Impulse.ar(800),  '/the_answer', [ hasfreq,K2A.ar(freqb)], idnum);

		}).store;

^super.newCopyArgs(dur,inchan ,outchan,freq_to_find);


}

// so you can peek at the uuid
uuidinfo {^uuid.postln;}

listen {
uuid=UniqueID.next.asSymbol;
/*uuid.postln;
freq_to_find.postln;*/

ofset = Array.new(freq_to_find.size);
ofset	= Array.fill(freq_to_find.size, {nil});
cv_to_use =Array.new(freq_to_find.size);
cv_to_use	= Array.fill(freq_to_find.size, {nil});

		"gerrrrrrrrrr".postln;
ofun = 	OSCFunc({ |msg|  var difamount;

			//msg.at(3).postln;
            //for each msg from \pich_pass_sweep check all freqs ni array
                 freq_to_find.do({arg freq,index;
			// 	//if nill add if now check if closer match replace
			 	 difamount= (msg.at(3)-freq).abs;
				//if nill
			          if( ofset.at(index) == nil ,
					     { cv_to_use.put(index,msg.at(4)); ofset.put(index,difamount);},
					//if not nill
					     {if( ofset.at(index) > difamount ,{cv_to_use.put(index,msg.at(4));ofset.put(index,difamount);});
                       })
				;});


			//msg.postln;
		},'/the_answer');

}

sweep{
		"This will take".postln;
		dur.postln;
		"secends".postln;
		Synth.new(\joevolt,[\idnum,uuid, \out,outchan,\in,inchan,\dur,dur,\freq ,0,\output_range ,[0,1]]);
		TempoClock.default.sched(dur, { "done sweep".postln; ofun.free; nil });

	}
//build kdtree of soundcard values linked to freq
build_tree {

freqto_cv_volt= [];
freq_to_find.size.do({arg donm; freqto_cv_volt = freqto_cv_volt.add( [freq_to_find.at(donm), cv_to_use.at(donm)])});
tree = KDTree(freqto_cv_volt);

	}
// outs kdtree to be pasted into event type \freqtocv
	out_tree {^tree}
	}










