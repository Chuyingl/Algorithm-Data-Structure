public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        //The retrun lists
        List<List<String>>ladder=new ArrayList<>();
        //add start and end in to the dict
        dict.add(start);
        dict.add(end);
        //constcruct two maps to store the next word list for each word in dict 
        HashMap<String, List<String>>map=new HashMap<>();
        //construct the map to store distacne
        HashMap<String, Integer>distance=new HashMap<>();
        //count the distance of each next word
        bfs(dict,end,start,map,distance);
        List<String>path=new ArrayList<>();
        //dfs, find each path and put in ladder
        dfs(path, ladder, dict, start,end,map, distance);
        return ladder;
    }
    //bfs, construct map and distance
   void bfs(Set<String>dict, String end, String start, Map<String,List<String>>map, Map<String, Integer>distance){
        // create null list for each word
        for(String i: dict){
            map.put(i,new ArrayList<String>());
        }
        Queue<String>q=new LinkedList<String>();
        //put strat in q
        q.offer(start);
        distance.put(start,0);
        //construct map
       while(!q.isEmpty()){
           String cur =q.poll();
           List<String> nextlist= findnext(cur,dict);
           for(String next: nextlist){
               map.get(cur).add(next);
               if(!distance.containsKey(next)){
                   distance.put(next,distance.get(cur)+1);
                    q.offer(next);
               }
              
           }
       }
           
       }
    public List<String>findnext(String cur, Set<String>dict){
        List<String>nextlist=new ArrayList<>();
        // find the next word lsits
        for(int i=0; i<cur.length(); i++){
            //replace the char 
            for(char a='a';a<='z';a++){
                if(a!=cur.charAt(i)){
                    String replace= cur.substring(0,i)+a+cur.substring(i+1);
                    if(dict.contains(replace)){
                        nextlist.add(replace);
                    }
                }
            }
        }
        return nextlist;
    } 
    // dfs, find the each possible path
   void dfs(List<String>path, List<List<String>>ladder, Set<String>dict, 
                    String start, String cur, HashMap<String, List<String>>map, HashMap<String, Integer>distance)
            {
                path.add(cur);
                //define the out rule
                if(cur.equals(start)){
                    Collections.reverse(path);
                    ladder.add(new ArrayList<String>(path));
                    //in order to find another path , it should be recovered
                    Collections.reverse(path);
                }
                else{
                    for(String next: map.get(cur)){
                        //beacause it start from the end, so the distance should cur=nect+1
                        if(distance.containsKey(next)&&distance.get(cur)==distance.get(next)+1){
                            dfs(path, ladder, dict, start,next,map, distance);
                        }
                }
            }
         path.remove(path.size()-1);
}
}