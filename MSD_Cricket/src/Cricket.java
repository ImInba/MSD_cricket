import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

class Team{
    String name;        //Team name
    String[] players;   //list of players in team
    int[] player_type;  // type 1->batsman 2->all rounder 3->bowler
    String[] bowlers;   //list of bowlers
    int[] bowler_type;  //type 1->fast 2->medium fast 3->fast
    String captain;
    int score,wickets,extras;
    float overs;
    int[] overs_bowled;
    int[] batsmen_scores;
    Team(String name,String[] players,int[] player_type,String[] bowlers,int[] bowler_type,String captain){
        this.name = name;
        this.players = players;
        this.player_type = player_type;
        this.bowlers = bowlers;
        this.bowler_type = bowler_type;
        this.captain = captain;
        this.score = this.wickets = 0;
        this.overs_bowled = new int[bowlers.length];
        this.batsmen_scores = new int[players.length];
        this.extras = 0;
        this.overs = 0.0f;
    }
    String getName(){
        return this.name;
    }
    String[] getPlayers(){
        return this.players;
    }
    int[] getPlayer_type(){
        return this.player_type;
    }
    String[] getBowlers(){
        return this.bowlers;
    }
    int[] getBowler_type(){
        return this.bowler_type;
    }
    String getCaptain(){
        return this.captain;
    }
}
public class Cricket {
    static HashMap<String,Team> teams = new HashMap<>();
    static String[] team_list = {"india","srilanka"};
    static Random r = new Random();
    static String endmark = "----------------------------------------------------------------------";
    public static void buildTeams(){
        String name = "india";
        String[] players = {"Rohit Sharma","Shikar Dhawan","Virat Kohli","Manish Pandey","MS Dhoni","Hardik Pandya","Dinesh Karthik","Axar Patel","Jasprit Bumrah","Bhuvaneshwar Kumar","Mohammed Shami"};
        int[] player_type = {1,1,1,1,1,2,1,2,3,3,3};
        String[] bowlers = {"Hardik Pandya","Axar Patel","Jasprit Bumrah","Bhuvaneshwar Kumar","Mohammed Shami"};
        int[] bowler_type = {2,1,3,3,3};
        String captain = players[2];
        teams.put(name,new Team(name,players,player_type,bowlers,bowler_type,captain));
        name = "srilanka";
        players = new String[]{"Danushka Gunathilaka","Upul Tharanga","Lahiru Thirimanne","Angelo Mathews","Niroshan Dickwella","Asela Gunaratne","Thisara Perera","Sachith Pathirana","Suranga Lakmal","Akila Dananjaya","Nuwan Pradeep"};
        player_type = new int[]{1,1,1,2,1,1,2,2,3,3,3};
        bowlers = new String[]{"Angelo Mathews","Thisara Perera","Sachith Pathirana","Suranga Lakmal","Akila Dananjaya","Nuwan Pradeep"};
        bowler_type = new int[]{2,2,1,2,1,3};
        captain = players[3];
        teams.put(name,new Team(name,players,player_type,bowlers,bowler_type,captain));
    }
    public static void printTeam(Team team){
        System.out.println(team.getName()+"'s Players");
        System.out.println(endmark);
        for(String player:team.getPlayers()){
            System.out.println(player);
        }
        System.out.println();
    }
    public static int getToss(String team1,String team2){
        int tossvalue = r.nextInt(2);
        return tossvalue;
    }
    public static String getRandomLocation(){
        String[] loc = new String[]{"point","short leg","fine leg","backward point","third man","mid-wicket","deep mid-wicket","covers","long off","long on","deep square leg","long leg"};
        return loc[r.nextInt(loc.length)];
    }
    public static void play(Team team1,Team team2,int overs,int innings) throws Exception{
        System.out.println("Umpires and "+team2.getName()+"'s players are walking into the field");
        Thread.sleep(1000);
        int striker,nonstriker,bowler = -1,temp = -1;
        int batsman_mark = 2,rvalue;
        int MAX_overs = (int)Math.ceil(overs/5.0);
        striker = 0;
        nonstriker = 1;
        boolean switchFlag = false;
        System.out.println(team1.players[striker]+" and "+team1.players[nonstriker]+" are making a roaring entry");
        Thread.sleep(1000);
        System.out.println(endmark);
        int over = 0,ball = 0;
        team1.overs = overs;
        for(over=1;over<=overs&&team1.wickets!=10;over++) {
            Thread.sleep(1000);
            int bowler_mark = r.nextInt(team2.bowlers.length);
            while (team2.overs_bowled[bowler_mark] >= MAX_overs&&bowler==bowler_mark)
                bowler_mark = r.nextInt(team2.bowlers.length);
            bowler = bowler_mark;
            team2.overs_bowled[bowler_mark] += 1;
            String type = team2.bowler_type[bowler]==1?"spin":team2.bowler_type[bowler]==2?"medium fast":"fast";
            System.out.println("Team: "+team1.getName()+"\tscore: "+team1.score+"/"+team1.wickets);
            System.out.println("Bowler: "+team2.bowlers[bowler]+"\ttype:"+type+"\t(over:"+over+")\n"+endmark+"\n");
            for (ball = 1; ball <= 6; ball++) {
                Thread.sleep(500);
                System.out.print("BALL "+ball+"\t:");
                switchFlag = false;
                rvalue = r.nextInt(9);
                if (rvalue == 0) {
                    rvalue = r.nextInt(2);
                    if (rvalue == 0)
                        System.out.println("Great ball..Beaten...");
                    else
                        System.out.println(team1.players[striker] + " tactfully leaves the ball..");
                } else if (rvalue <= 6) {
                    switch (rvalue) {
                        case 1:
                            System.out.println(team1.players[striker] + " takes a single hitting the ball to " + getRandomLocation());
                            break;
                        case 2:
                            System.out.println(team1.players[striker] + " takes a two hitting the ball to " + getRandomLocation());
                            break;
                        case 3:
                            System.out.println(team1.players[striker] + " does a lightning strike to " + getRandomLocation() + " and gets three!!");
                            break;
                        case 4:
                            System.out.println(team1.players[striker] + " hits a magneficient four to " + getRandomLocation());
                            break;
                        case 5:
                            System.out.println(team1.players[striker] + " plans for a single at " + getRandomLocation() + " and it ends in an overthrown four. Gets FIVE!!");
                            break;
                        case 6:
                            System.out.println(team1.players[striker] + " is a beast!. Great strike to six over " + getRandomLocation());
                            break;
                    }
                    team1.score += rvalue;
                    team1.batsmen_scores[striker] += rvalue;
                    if (rvalue % 2 == 1)
                        switchFlag = true;
                } else if (rvalue == 7) {
                    rvalue = r.nextInt(3);
                    if (rvalue == 0) {
                        rvalue = r.nextInt(11);
                        if (team2.players[rvalue].equals(team2.bowlers[bowler_mark]))
                            System.out.println(team1.players[striker] + " caught out!! (C and B " + bowler + " )");
                        else
                            System.out.println(team1.players[striker] + " caught out!! (C " + team2.players[rvalue] + " B " + team2.bowlers[bowler] + " )");
                    } else if (rvalue == 2)
                        System.out.println(team2.bowlers[bowler] + " strikes the stumps!! Wipes " + team1.players[striker] + " and the batsman walks out!");
                    else {
                        rvalue = r.nextInt(11);
                        System.out.println(team1.players[striker] + " run out(" + team2.players[rvalue] + ")");
                    }
                    team1.wickets += 1;
                    if (team1.wickets == 10) {
                        break;
                    }
                    striker = batsman_mark++;
                } else {
                    rvalue = r.nextInt(3);
                    if (rvalue == 0) {
                        System.out.println("Wide ball");
                        team1.score += 1;
                        team1.extras += 1;
                        ball--;
                    } else if (rvalue == 1) {
                        System.out.println("No ball");
                        team1.score += 1;
                        team1.extras += 1;
                        ball--;
                    } else {
                        int bye_runs = r.nextInt(2)+1;
                        System.out.println("Batsman take " + bye_runs + " runs as byes");
                        if (bye_runs % 2 == 1)
                            switchFlag = true;
                        team1.score += bye_runs;
                        team1.extras += bye_runs;
                    }
                }
                if (switchFlag == true) {
                    temp = striker;
                    striker = nonstriker;
                    nonstriker = temp;
                }
                if (innings == 2) {
                    if (team1.score > team2.score) {
                        break;
                    }
                    if (team1.score == team2.score)
                        System.out.println("scores level!!..");
                }
            }
            if(team1.wickets==10||(innings==2&&team1.score>team2.score)) {
                if(ball>=6)
                    team1.overs = over+1;
                else
                    team1.overs = (float)(over-1+(ball/10.0));
                break;
            }
            System.out.println(endmark);
            temp = striker;
            striker = nonstriker;
            nonstriker = temp;
        }
        System.out.println("Team: "+team1.getName()+"\tscore: "+team1.score+"/"+team1.wickets);
        System.out.println("\n"+team1.getName()+"'s innings has finished\n");
        Thread.sleep(1500);
        if(innings==2){
            System.out.println(team2.getName()+" scored "+team2.score+" for "+team2.wickets+" in "+team2.overs+"overs\n");
            System.out.println(team1.getName()+" scored "+team1.score+" for "+team1.wickets+" in "+team1.overs+"overs\n");
            if(team1.score==team2.score){
                System.out.println("Once in a blue moon!! The match ended in a draw!!");
            }
            else if(team1.score>team2.score){
                System.out.println(team1.getName()+" won by "+(10-team1.wickets)+" wickets!!");
            }
            else
                System.out.println(team2.getName()+" won by "+(team2.score-team1.score)+" runs");
        }
    }
    public static void start(Team team1,Team team2,int overs) throws Exception{
        System.out.println(team1.getName()+" vs "+team2.getName()+"("+overs+" overs match)\n");
        System.out.println("Maximum overs per bowler is "+(int)Math.ceil(overs/5.0));
        printTeam(team1);
        printTeam(team2);
        System.out.println(endmark);
        System.out.println("Captain "+team1.captain+" of "+team1.getName()+" is tossing the coin");
        Thread.sleep(2000);
        int toss_call = r.nextInt(2);
        System.out.println((toss_call==0?"Tails":"Heads")+" is the call by captain "+team2.captain+" of "+team2.getName()+" ...");
        int toss_value = getToss(team1.getName(),team2.getName());
        Thread.sleep(2000);
        System.out.println("andddd......"+(toss_value==0?"Tails":"Heads")+" it is..");
        System.out.print("Team "+(toss_value==toss_call?team2.getName():team1.getName())+" has won the toss ");
        int decision = r.nextInt(2);
        System.out.println("and they decided to "+(decision==0?"BAT":"FIELD")+" first");
        System.out.println(endmark);
        if((toss_call==toss_value&&decision==1)||(toss_call!=toss_value&&decision==0)) {
            play(team1, team2, overs,1);
            play(team2, team1, overs,2);
        }
        else {
            play(team2, team1, overs,1);
            play(team1, team2, overs,2);
        }
    }
    public static void main(String[] args) throws Exception {
        buildTeams();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String team1,team2;
        int overs;
        System.out.print("Enter team1: ");
        team1 = br.readLine().toLowerCase();
        if(!teams.containsKey(team1)) {
            System.out.println("Team "+team1+" not available...Random team is being picked..");
            team1 = team_list[(r.nextInt(10))%team_list.length].toString();
            System.out.println("Team "+team1+" is picked randomly");
        }
        System.out.print("Enter team2: ");
        team2 = br.readLine().toLowerCase();
        if(!teams.containsKey(team2)||team1.equals(team2)){
            System.out.println("Team "+team2+" not available...Random team is being picked..");
            team2 = team_list[(r.nextInt(10))%team_list.length].toString();
            while(team1.equals(team2))
                team2 = team_list[(r.nextInt(10))%team_list.length].toString();
            System.out.println("Team "+team2+" is picked randomly");
        }
        System.out.print("Enter overs: ");
        overs = Integer.parseInt(br.readLine());
        System.out.println(endmark);
        start(teams.get(team1),teams.get(team2),overs);
    }
}
