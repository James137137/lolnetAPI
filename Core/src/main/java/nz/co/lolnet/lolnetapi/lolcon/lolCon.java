package nz.co.lolnet.lolnetapi.lolcon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.rmi.NoSuchObjectException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import nz.co.lolnet.lolnetapi.settings.Settings;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author cptwin
 */
public class lolCon {

    public static void registerNewPlayer(String authHash, String playername) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {
        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
        data += "&" + URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playername + "", "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/registernewplayer.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();
        wr.close();
    }

    public static long getPlayerBalance(String playername) throws UnsupportedEncodingException, IOException, ParseException {
        String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playername, "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/getplayerbalance.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());

        wr.close();
        rd.close();

        return (long) json.get("playerbalance");
    }

    public static double getPlayerVoteValueMultiplier(String playername) throws UnsupportedEncodingException, IOException, org.json.simple.parser.ParseException {
        String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playername, "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/getplayervotevaluemultiplier.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(1000);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());

        wr.close();
        rd.close();

        return Double.parseDouble(json.get("playermultiplier").toString());
    }

    public static long getPlayerBonusClaimBlocks(String playername) throws UnsupportedEncodingException, IOException, ParseException {
        String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playername, "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/getplayerbonusclaimblocks.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());

        wr.close();
        rd.close();

        return (long) json.get("playerbonusclaimblocks");
    }

    public static String getPlayerTitle(String playername) throws UnsupportedEncodingException, IOException, ParseException {
        String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playername, "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/getplayertitle.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());

        wr.close();
        rd.close();

        return (String) json.get("playerTitle");
    }

    public static boolean updatetPlayerTitle(String playerName, String newTitle,String authhash) {
        boolean result = false;
        try {
            // Construct data

            String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playerName, "UTF-8");
            data += "&" + URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authhash, "UTF-8");
            data += "&" + URLEncoder.encode("newtitle", "UTF-8") + "=" + URLEncoder.encode(newTitle, "UTF-8");
            // Send data
            URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/updateplayertitle.php");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = rd.readLine()) != null) {
                if (line.toLowerCase().contains("true")) {
                    result = true;
                    break;
                }
            }
            wr.close();
            rd.close();
        }
        catch (Exception e) {
            return result;
        }
        return result;
    }

    public static String getPlayerNick(String playername) throws UnsupportedEncodingException, IOException, ParseException {
        String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playername, "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/getplayernick.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());

        wr.close();
        rd.close();

        return (String) json.get("playerNickname");
    }

    public static String getPlayerNameFromNick(String playerNick) throws UnsupportedEncodingException, IOException, ParseException {
        String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playerNick, "UTF-8");

        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/getplayerfromnick.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());

        wr.close();
        rd.close();

        return (String) json.get("playerName");
    }

    public static boolean updatetPlayerNick(String playerName, String newNick,String authhash) {
    boolean result = false;
        try {
            // Construct data

            String data = URLEncoder.encode("playername", "UTF-8") + "=" + URLEncoder.encode(playerName, "UTF-8");
            data += "&" + URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authhash, "UTF-8");
            data += "&" + URLEncoder.encode("newnick", "UTF-8") + "=" + URLEncoder.encode(newNick, "UTF-8");
            // Send data
            URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/updateplayernick.php");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = rd.readLine()) != null) {
                if (line.toLowerCase().contains("true")) {
                    result = true;
                    break;
                }
            }
            wr.close();
            rd.close();
        }
        catch (Exception e) {
            return result;
        }
        return result;
    }

    public static HashMap<String, Long> getTop10MonthlyVoters() throws IOException, ParseException {
        HashMap<String, Long> output = new HashMap<>();

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/gettop10monthlyvotes.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);

        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());
        rd.close();

        Iterator<?> keys = json.keySet().iterator();
        while (keys.hasNext()) {
            String username = (String) keys.next();
            long votenum = (long) json.get(username);
            output.put(username, votenum);
        }

        return output;
    }

    @Deprecated
    public static HashMap<String, Integer> getForumGroups(String authHash) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {
        return getForumGroupsNameKey(authHash);
    }

    public static HashMap<String, Integer> getForumGroupsNameKey(String authHash) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {
        HashMap<String, Integer> output = new HashMap<>();

        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/getforumgroups.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());

        wr.close();
        rd.close();

        Iterator<?> keys = json.keySet().iterator();

        while (keys.hasNext()) {
            String group_id = (String) keys.next();
            String group_name = (String) json.get(group_id);
            output.put(group_name, Integer.parseInt(group_id));
        }

        return output;
    }

    public static HashMap<Integer, String> getForumGroupsIDKey(String authHash) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {
        HashMap<Integer, String> output = new HashMap<>();

        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/getforumgroups.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());

        wr.close();
        rd.close();

        Iterator<?> keys = json.keySet().iterator();

        while (keys.hasNext()) {
            String group_id = (String) keys.next();
            String group_name = (String) json.get(group_id);
            output.put(Integer.parseInt(group_id), group_name);
        }

        return output;
    }

    public static int getForumUserID(String authHash, String username) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {
        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
        data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/getforumuserid.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();
        
        // Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());
        
        wr.close();
        rd.close();

            Iterator<?> keys = json.keySet().iterator();

            while (keys.hasNext()) {
                String user = (String) keys.next();
                long forumid = (long) json.get(user);
                return safeLongToInt(forumid);
            }
        throw new NoSuchObjectException("Username not found in Database!");
        }

    public static ArrayList<Integer> getForumUserForumGroups(String authHash, int userForumID) throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException {
        ArrayList<Integer> output = new ArrayList<>();

        String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
        data += "&" + URLEncoder.encode("userforumid", "UTF-8") + "=" + URLEncoder.encode(userForumID + "", "UTF-8");

        // Send data
        URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/getforumuserforumgroups.php");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(Settings.httpTimeOut);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JSONArray json = (JSONArray) new JSONParser().parse(rd.readLine());

        wr.close();
        rd.close();

        for (Object o : json) {
            output.add(Integer.parseInt(o.toString()));
        }

        return output;
    }

    public static boolean addUserToForumGroup(String authHash, String playerName, int groupID) throws MalformedURLException, IOException, UnsupportedEncodingException, ParseException {
        boolean success = false;
        int playerForumID = getForumUserID(authHash, playerName);
        if (!userAlreadyBelongsToGroup(authHash, playerForumID, groupID)) {
            String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
            data += "&" + URLEncoder.encode("groupid", "UTF-8") + "=" + URLEncoder.encode(groupID + "", "UTF-8");
            data += "&" + URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(playerForumID + "", "UTF-8");

            URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/addusertoforumgroup.php");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(Settings.httpTimeOut);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());
            success = (boolean) json.get("success");

            wr.close();
            rd.close();
        }
        return success;
    }

    public static boolean removeUserFromForumGroup(String authHash, String playerName, int groupID) throws MalformedURLException, IOException, UnsupportedEncodingException, ParseException {
        boolean success = false;
        int playerForumID = getForumUserID(authHash, playerName);
        if (userAlreadyBelongsToGroup(authHash, playerForumID, groupID)) {
            String data = URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
            data += "&" + URLEncoder.encode("groupid", "UTF-8") + "=" + URLEncoder.encode(groupID + "", "UTF-8");
            data += "&" + URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(playerForumID + "", "UTF-8");

            URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/removeuserfromforumgroup.php");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(Settings.httpTimeOut);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JSONObject json = (JSONObject) new JSONParser().parse(rd.readLine());
            success = (boolean) json.get("success");

            wr.close();
            rd.close();
        }

        return success;
    }

    public static long getLastVoted(String authHash, String playerName, String serverName, String serviceName) {
        try {
            // Construct data

            String data = URLEncoder.encode("playerName", "UTF-8") + "=" + URLEncoder.encode(playerName, "UTF-8");
            data += "&" + URLEncoder.encode("authhash", "UTF-8") + "=" + URLEncoder.encode(authHash, "UTF-8");
            data += "&" + URLEncoder.encode("serverName", "UTF-8") + "=" + URLEncoder.encode(serverName, "UTF-8");
            data += "&" + URLEncoder.encode("serviceName", "UTF-8") + "=" + URLEncoder.encode(serviceName, "UTF-8");
            // Send data
            URL url = new URL("https://www.lolnet.co.nz/api/v1.0/lolcoins/gettimelastvoted.php");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JSONArray json = (JSONArray) new JSONParser().parse(rd.readLine());

            wr.close();
            rd.close();

            
            for (Object o : json) {
                return (Timestamp.valueOf(o.toString()).getTime());
            }

        } catch (IOException | ParseException e) {
        }

        return 0;
    }

    private static boolean userAlreadyBelongsToGroup(String authHash, int playerID, int groupID) throws MalformedURLException, IOException, UnsupportedEncodingException, ParseException {
        boolean isInGroup = false;
        for (int groups : getForumUserForumGroups(authHash, playerID)) {
            if (groups == groupID) {
                isInGroup = true;
            }
        }
        return isInGroup;
    }

    private static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

}