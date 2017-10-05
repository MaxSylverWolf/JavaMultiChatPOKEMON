package ChatAppMain;

import javax.swing.ImageIcon;

public class ChatEmoticons{
	ImageIcon masterball = new ImageIcon(ChatFrame.class.getResource("/1.gif"));
	ImageIcon waterball = new ImageIcon(ChatFrame.class.getResource("/2.gif"));
	ImageIcon pokeball = new ImageIcon(ChatFrame.class.getResource("/3.gif"));
	ImageIcon pikachu = new ImageIcon(ChatFrame.class.getResource("/4.gif"));
	ImageIcon ghastly = new ImageIcon(ChatFrame.class.getResource("/5.gif"));
	ImageIcon monkey = new ImageIcon(ChatFrame.class.getResource("/6.gif"));
	ImageIcon angryghastly = new ImageIcon(ChatFrame.class.getResource("/7.gif"));
	ImageIcon woobuffet = new ImageIcon(ChatFrame.class.getResource("/8.gif"));
	ImageIcon fly = new ImageIcon(ChatFrame.class.getResource("/9.gif"));
	ImageIcon jolteon = new ImageIcon(ChatFrame.class.getResource("/10.gif"));
	ImageIcon flareon = new ImageIcon(ChatFrame.class.getResource("/11.gif"));
	ImageIcon green = new ImageIcon(ChatFrame.class.getResource("/12.gif"));
	ImageIcon runaway = new ImageIcon(ChatFrame.class.getResource("/13.gif"));
	ImageIcon togepi = new ImageIcon(ChatFrame.class.getResource("/14.gif"));
	ImageIcon gengar = new ImageIcon(ChatFrame.class.getResource("/15.gif"));
	ImageIcon whitefeel = new ImageIcon(ChatFrame.class.getResource("/16.gif"));
	ImageIcon lucario = new ImageIcon(ChatFrame.class.getResource("/17.gif"));
	ImageIcon bird = new ImageIcon(ChatFrame.class.getResource("/18.gif"));
	ImageIcon exeggute = new ImageIcon(ChatFrame.class.getResource("/19.gif"));
	ImageIcon vileplum = new ImageIcon(ChatFrame.class.getResource("/21.gif"));
	ImageIcon charmeleon = new ImageIcon(ChatFrame.class.getResource("/22.gif"));
	ImageIcon psyduck = new ImageIcon(ChatFrame.class.getResource("/23.gif"));
	ImageIcon loveflareon = new ImageIcon(ChatFrame.class.getResource("/24.gif"));
	ImageIcon umbreon = new ImageIcon(ChatFrame.class.getResource("/25.gif"));
	ImageIcon bear = new ImageIcon(ChatFrame.class.getResource("/26.gif"));
	ImageIcon angryjolteon = new ImageIcon(ChatFrame.class.getResource("/27.gif"));
	ImageIcon eevee = new ImageIcon(ChatFrame.class.getResource("/28.gif"));
	ImageIcon snivy = new ImageIcon(ChatFrame.class.getResource("/29.gif"));
	ImageIcon ditto = new ImageIcon(ChatFrame.class.getResource("/30.gif"));
	ImageIcon turtle = new ImageIcon(ChatFrame.class.getResource("/31.gif"));
	ImageIcon leafeon = new ImageIcon(ChatFrame.class.getResource("/32.gif"));
	ImageIcon glaceon = new ImageIcon(ChatFrame.class.getResource("/33.gif"));
	ImageIcon jigglypuff = new ImageIcon(ChatFrame.class.getResource("/34.gif"));
	ImageIcon miltank = new ImageIcon(ChatFrame.class.getResource("/35.gif"));
	ImageIcon bulbasaur = new ImageIcon(ChatFrame.class.getResource("/36.gif"));
	ImageIcon charmander = new ImageIcon(ChatFrame.class.getResource("/37.gif"));
	ImageIcon mudkip = new ImageIcon(ChatFrame.class.getResource("/38.gif"));
	ImageIcon bleeh = new ImageIcon(ChatFrame.class.getResource("/20.gif"));
	ImageIcon cindaquil = new ImageIcon(ChatFrame.class.getResource("/39.gif"));
	ImageIcon totodile = new ImageIcon(ChatFrame.class.getResource("/40.gif"));
	ImageIcon runjolteon = new ImageIcon(ChatFrame.class.getResource("/41.gif"));
	ImageIcon chikorita = new ImageIcon(ChatFrame.class.getResource("/42.gif"));
    static final String masterball_E = "(masterball)",
    		waterball_E = "(waterball)",
    		pokeball_E = "(pokeball)",
    		pikachu_E = "(pikachu)",
    		ghastly_E = "(ghastly)",
    		monkey_E = "(monkey)",
    		angryghastly_E = "(angryghastly)",
    		woobuffet_E = "(woobuffet)",
    		fly_E = "(fly)",
    		jolteon_E = "(jolteon)",
    		flareon_E = "(flareon)",
    		green_E = "(green)",
    		runaway_E = "(runaway)",
    		togepi_E = "(togepi)",
    		gengar_E = "(gengar)",
    		whitefeel_E = "(whitefeel)",
    		lucario_E = "(lucario)",//
    		bird_E = "(bird)",
    		exeggute_E = "(exeggute)",
    		vileplum_E = "(vileplum)",
    		charmeleon_E = "(charmeleon)",
    		psyduck_E = "(psyduck)",
    		loveflareon_E = "(loveflareon)",
    		umbreon_E = "(umbreon)",
    		bear_E = "(bear)",
    		angryjolteon_E = "(angryjolteon)",
    		eevee_E = "(eevee)",
    		snivy_E = "(snivy)",
    		ditto_E = "(ditto)",
    		turtle_E = "(turtle)",
    		leafeon_E = "(leafeon)",
    		glaceon_E = "(glaceon)",
    		jigglypuff_E = "(jigglypuff)",
    		miltank_E = "(miltank)",
    		bulbasaur_E = "(bulbasaur)",
    		charmander_E = "(charmander)",
    		mudkip_E = "(mudkip)",
    		bleeh_E = "(bleeh)",
    		cindaquil_E = "(cindaquil)",
    		totodile_E = "(totodile)",
    		runjolteon_E = "(runjolteon)",
    		chikorita_E = "(chikorita)";
	String[] emoticons = {masterball_E, waterball_E, pokeball_E, pikachu_E,ghastly_E, monkey_E, angryghastly_E, woobuffet_E, fly_E, jolteon_E, flareon_E, green_E, runaway_E, togepi_E,
    		gengar_E, whitefeel_E, lucario_E, bird_E, exeggute_E, vileplum_E, charmeleon_E, psyduck_E, loveflareon_E, umbreon_E, bear_E, angryjolteon_E, eevee_E, snivy_E, ditto_E,
    		turtle_E, leafeon_E, glaceon_E, jigglypuff_E, miltank_E, bulbasaur_E, charmander_E, mudkip_E, bleeh_E, cindaquil_E, totodile_E, runjolteon_E, chikorita_E};
	public String replace(String s){
		   s=s.replaceAll("\\(masterball\\)", " \\(masterball\\) ");
		   s=s.replaceAll("\\(waterball\\)", " \\(waterball\\) ");
		   s=s.replaceAll("\\(pokeball\\)", " \\(pokeball\\) ");
		   s=s.replaceAll("\\(pikachu\\)", " \\(pikachu\\) ");
		   s=s.replaceAll("\\(ghastly\\)", " \\(ghastly\\) ");
		   s=s.replaceAll("\\(monkey\\)", " \\(monkey\\) ");
		   s=s.replaceAll("\\(angryghastly\\)", " \\(angryghastly\\) ");
		   s=s.replaceAll("\\(woobuffet\\)", " \\(woobuffet\\) ");
		   s=s.replaceAll("\\(fly\\)", " \\(fly\\) ");
		   s=s.replaceAll("\\(jolteon\\)", " \\(jolteon\\) ");
		   s=s.replaceAll("\\(flareon\\)", " \\(flareon\\) ");
		   s=s.replaceAll("\\(green\\)", " \\(green\\) ");
		   s=s.replaceAll("\\(runaway\\)", " \\(runaway\\) ");
		   s=s.replaceAll("\\(togepi\\)", " \\(togepi\\) ");
		   s=s.replaceAll("\\(gengar\\)", " \\(gengar\\) ");
		   s=s.replaceAll("\\(whitefeel\\)", " \\(whitefeel\\) ");
		   s=s.replaceAll("\\(lucario\\)", " \\(lucario\\) ");
		   s=s.replaceAll("\\(bird\\)", " \\(bird\\) ");
		   s=s.replaceAll("\\(exeggute\\)", " \\(exeggute\\) ");
		   s=s.replaceAll("\\(vileplum\\)", " \\(vileplum\\) ");
		   s=s.replaceAll("\\(charmeleon\\)", " \\(charmeleon\\) ");
		   s=s.replaceAll("\\(psyduck\\)", " \\(psyduck\\) ");
		   s=s.replaceAll("\\(loveflareon\\)", " \\(loveflareon\\) ");
		   s=s.replaceAll("\\(umbreon\\)", " \\(umbreon\\) ");
		   s=s.replaceAll("\\(bear\\)", " \\(bear\\) ");
		   s=s.replaceAll("\\(angryjolteon\\)", " \\(angryjolteon\\) ");
		   s=s.replaceAll("\\(eevee\\)", " \\(eevee\\) ");
		   s=s.replaceAll("\\(snivy\\)", " \\(snivy\\) ");
		   s=s.replaceAll("\\(ditto\\)", " \\(ditto\\) ");
		   s=s.replaceAll("\\(turtle\\)", " \\(turtle\\) ");
		   s=s.replaceAll("\\(leafeon\\)", " \\(leafeon\\) ");
		   s=s.replaceAll("\\(glaceon\\)", " \\(glaceon\\) ");
		   s=s.replaceAll("\\(jigglypuff\\)", " \\(jigglypuff\\) ");
		   s=s.replaceAll("\\(miltank\\)", " \\(miltank\\) ");
		   s=s.replaceAll("\\(bulbasaur\\)", " \\(bulbasaur\\) ");
		   s=s.replaceAll("\\(charmander\\)", " \\(charmander\\) ");
		   s=s.replaceAll("\\(mudkip\\)", " \\(mudkip\\) ");
		   s=s.replaceAll("\\(bleeh\\)", " \\(bleeh\\) ");
		   s=s.replaceAll("\\(cindaquil\\)", " \\(cindaquil\\) ");
		   s=s.replaceAll("\\(totodile\\)", " \\(totodile\\) ");
		   s=s.replaceAll("\\(runjolteon\\)", " \\(runjolteon\\) ");
		   s=s.replaceAll("\\(chikorita\\)", " \\(chikorita\\) ");
		   s=s.replaceAll("  ", " ");
		return s;
	}
	public ImageIcon emoAction(String emoticon){
		ImageIcon re = new ImageIcon();
		switch (emoticon) {
        case masterball_E: re = masterball;break;
        case waterball_E: re = waterball;break;
        case pokeball_E: re = pokeball;break;
        case pikachu_E:re = pikachu;break;
        case ghastly_E: re = ghastly;break;
        case monkey_E: re = monkey;break;
        case angryghastly_E: re = angryghastly;break;
        case woobuffet_E: re = woobuffet;break;
        case fly_E: re = fly;break;
        case jolteon_E: re = jolteon;break;
        case flareon_E: re = flareon;break;
        case green_E: re = green;break;
        case runaway_E: re = runaway;break;
        case togepi_E: re = togepi;break;
        case gengar_E: re = gengar;break;
        case whitefeel_E: re = whitefeel;break;
        case lucario_E: re = lucario;break;
        case bird_E: re = bird;break;
        case exeggute_E: re = exeggute;break;
        case vileplum_E: re = vileplum;break;
        case charmeleon_E: re = charmeleon;break;
        case psyduck_E: re = psyduck;break;
        case loveflareon_E: re = loveflareon;break;
        case umbreon_E: re = umbreon;break;
        case bear_E: re = bear;break;
        case angryjolteon_E: re = angryjolteon;break;
        case eevee_E: re = eevee;break;
        case snivy_E: re = snivy;break;
        case ditto_E: re = ditto;break;
        case turtle_E: re = turtle;break;
        case leafeon_E: re = leafeon;break;
        case glaceon_E: re = glaceon;break;
        case jigglypuff_E: re = jigglypuff;break;
        case miltank_E: re = miltank;break;
        case bulbasaur_E: re = bulbasaur;break;
        case charmander_E: re = charmander;break;
        case mudkip_E: re = mudkip;break;
        case bleeh_E: re = bleeh;break;
        case cindaquil_E: re = cindaquil;break;
        case totodile_E: re = totodile;break;
        case runjolteon_E: re = runjolteon;break;
        case chikorita_E: re = chikorita;break;
    }
		return re;
	}
	public String mousePre(int a, int b){
		String re = new String();
		switch(b){
		case 0:switch(a){
			case 0:re = turtle_E;break;
			case 24:re = ghastly_E;break;
			case 48:re = exeggute_E;break;
			case 72:re = monkey_E;break;
			case 96:re = fly_E;break;
			case 120:re = woobuffet_E;break;
			case 144:re = angryghastly_E;break;}
			break;
		case 24:switch(a){
			case 0:re = flareon_E;break;
			case 24:re = green_E;break;
			case 48:re = togepi_E;break;
			case 72:re = whitefeel_E;break;
			case 96:re = runaway_E;break;
			case 120:re = lucario_E;break;
			case 144:re = bird_E;break;}
			break;
		case 48:switch(a){
			case 0:re = pokeball_E;break;
			case 24:re = vileplum_E;break;
			case 48:re = charmeleon_E;break;
			case 72:re = psyduck_E;break;
			case 96:re = loveflareon_E;break;
			case 120:re = umbreon_E;break;
			case 144:re = bear_E;break;}
			break;
		case 72:switch(a){
			case 0:re = angryjolteon_E;break;
			case 24:re = snivy_E;break;
			case 48:re = charmander_E;break;
			case 72:re = ditto_E;break;
			case 96:re = pikachu_E;break;
			case 120:re = leafeon_E;break;
			case 144:re = gengar_E;break;}
			break;
		case 96:switch(a){
			case 0:re = glaceon_E;break;
			case 24:re = jigglypuff_E;break;
			case 48:re = masterball_E;break;
			case 72:re = miltank_E;break;
			case 96:re = eevee_E;break;
			case 120:re = bulbasaur_E;break;
			case 144:re = mudkip_E;break;}
			break;
		case 120:switch(a){
			case 0:re = bleeh_E;break;
			case 24:re = cindaquil_E;break;
			case 48:re = totodile_E;break;
			case 72:re = runjolteon_E;break;
			case 96:re = chikorita_E;break;
			case 120:re = waterball_E;break;
			case 144:re = jolteon_E;break;}
			break;
		} return re;
	}
	public Boolean eCheck(String s){
		Boolean k = false;
		if(s.length()>57) s=s.substring(0, 57);
		for(String p:emoticons){
			if(s.contains(p)) k = true;
		}
		return k;
	}
}
