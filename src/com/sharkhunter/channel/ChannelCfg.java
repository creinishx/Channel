package com.sharkhunter.channel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.configuration.ConfigurationException;

import net.pms.PMS;

public class ChannelCfg {
	
	public static final int PROXY_DNS_NONE=0;
	public static final int PROXY_DNS_ALL=1;
	public static final int PROXY_DNS_CHANNEL=2;
	
	private String chPath;
	private String saPath;
	private String rtmpPath;
	private String scriptPath;
	private String sopcastPath;
	private String pplivePath;
	private String perlPath;
	private String pythonPath;
	private String get_flPath;
	private String ytPath;
	private String cookiePath;
	private Channels top;
	private boolean subs;
	private boolean cache;
	private String credPath;
	private boolean favorite;
	private String navixUploadList;
	private String navixUploadList2;
	private boolean noPlay;
	private boolean netDisc;
	private boolean rawSave;
	private boolean allPlay;
	private String proxyDNS;
	private int proxyDNSMode;
	private boolean longSaveName;
	private boolean oldSub;
	private boolean mp2force;
	
	public ChannelCfg(Channels top) {
		chPath=null;
		saPath=null;
		rtmpPath=null;
		scriptPath=null;
		cookiePath=null;
		subs=true;
		credPath=null;
		this.top=top;
		cache=false;
		favorite=true;
		navixUploadList=null;
		navixUploadList2=null;
		noPlay=false;
		netDisc=false;
		//netDisc=true;
		rawSave=false;
		allPlay=true;
		proxyDNS=null;
		proxyDNSMode=PROXY_DNS_NONE;
		longSaveName=false;
		oldSub=false;
		mp2force=true;
	}
	
	///////////////////////////////////
	// Set methods
	///////////////////////////////////
	
	public void setPath(String p) {
		chPath=p;
	}
	
	public void setSavePath(String p) {
		saPath=p;
	}
	
	public void setRtmpPath(String p) {
		rtmpPath=p;
	}
	
	public void setScriptPath(String p) {
		scriptPath=p;
	}
	
	public void setSopPath(String p) {
		sopcastPath=p;
	}
	
	public void setPPLivePath(String p) {
		pplivePath=p;
	}
	
	public void setPerlPath(String p) {
		perlPath=p;
	}
	
	public void setPythPath(String p) {
		pythonPath=p;
	}
	
	public void setGetFlPath(String p) {
		get_flPath=p;
	}
	
	public void setYouTubePath(String p) {
		ytPath=p;
	}
	
	public void setCookiePath(String p) {
		cookiePath=p;
	}
	
	public void setCredPath(String p) {
		credPath=p;
	}
	
	public void setNaviXUpload(String p) {
		String[] tmp=p.split(",");
		navixUploadList=p;
		navixUploadList2=null;
		if(tmp.length>1&&tmp[0].equals("local")) {
			navixUploadList=tmp[0];
			navixUploadList2=tmp[1];
		}
	}
	
	public void setFavorite(boolean b) {
		favorite=b;
	}
	
	public void setLongSvaeName(boolean b) {
		longSaveName=b;
	}
	
	////////////////////////////////////////
	// Get methods
	////////////////////////////////////////

	public String getPath() {
		return chPath;
	}
	
	public String getSavePath() {
		return saPath;
	}
	
	public String getRtmpPath() {
		return rtmpPath;
	}
	
	public String getScriptPath() {
		return scriptPath;
	}
	
	public String getSopPath() {
		return sopcastPath;
	}
	
	public String getPPLivePath() {
		return pplivePath;
	}
	
	public String getPerlPath() {
		return perlPath;
	}
	
	public String getPythonPath() {
		return pythonPath;
	}
	
	public String getFlashPath() {
		if(ChannelUtil.empty(get_flPath))
			return getScriptPath();
		return get_flPath;
	}
	
	public String getYouTubePath() {
		if(ChannelUtil.empty(ytPath))
			return getScriptPath();
		return ytPath;
	}
	
	public boolean getCache() {
		return cache;
	}
	
	public String getCookiePath() {
		return cookiePath;
	}
	
	public String getCredPath() {
		return credPath;
	}
	
	public boolean favorite() {
		return favorite;
	}
	
	public String getNaviXUpload() {
		return navixUploadList;
	}
	
	public String getNaviXUpload2() {
		return navixUploadList2;
	}
	
	public boolean noPlay() {
		return noPlay;
	}
	
	public boolean netDiscStyle() {
		return netDisc;
	}
	
	public boolean rawSave() {
		return rawSave;
	}
	
	public boolean allPlay() {
		return allPlay;
	}
	
	public int proxyDNSMode() {
		return proxyDNSMode;
	}
	
	public String proxyDNS() {
		return proxyDNS;
	}
	
	public boolean longSaveName() {
		return longSaveName;
	}
	
	public boolean oldSub() {
		return oldSub;
	}
	
	public String getCurlPath() {
		return (String) PMS.getConfiguration().getCustomProperty("curl.path");
	}
	
	public boolean mp2Force() {
		return mp2force;
	}
	
	////////////////////////////////////////
	// Misc. methods
	////////////////////////////////////////
	
	public String scriptFile(String file) {
		return scriptPath+File.separator+file;
	}
	
	//////////////////////////////////////
	// Other methods
	//////////////////////////////////////
	
	public void init() {
		// Paths
		chPath=Channels.getPath();
		saPath=Channels.getSavePath();
		scriptPath=(String) PMS.getConfiguration().getCustomProperty("pmsencoder.script.directory");
		rtmpPath=(String) PMS.getConfiguration().getCustomProperty("rtmpdump.path");
		sopcastPath=(String) PMS.getConfiguration().getCustomProperty("sopcast.path");
		pplivePath=(String) PMS.getConfiguration().getCustomProperty("pplive.path");
		perlPath=(String) PMS.getConfiguration().getCustomProperty("perl.path");
		pythonPath=(String) PMS.getConfiguration().getCustomProperty("python.path");
		get_flPath=(String) PMS.getConfiguration().getCustomProperty("get-flash-videos.path");
		ytPath=(String) PMS.getConfiguration().getCustomProperty("youtube-dl.path");
		cookiePath=(String) PMS.getConfiguration().getCustomProperty("cookie.path");
		credPath=(String) PMS.getConfiguration().getCustomProperty("cred.path");
		
		// Other
		String dbg=(String)PMS.getConfiguration().getCustomProperty("channels.debug");
		String sub=(String) PMS.getConfiguration().getCustomProperty("channels.subtitles");
		String cacheStr=(String) PMS.getConfiguration().getCustomProperty("channels.cache");
		String rtmpMode=(String)PMS.getConfiguration().getCustomProperty("channels.rtmp");
		String group=(String)PMS.getConfiguration().getCustomProperty("channels.group");
		String mi=(String)PMS.getConfiguration().getCustomProperty("channels.movieinfo");
		String fav=(String)PMS.getConfiguration().getCustomProperty("channels.favorite");
		String nop=(String)PMS.getConfiguration().getCustomProperty("channels.no_play");
		String nd=(String)PMS.getConfiguration().getCustomProperty("channels.net_disc");
		String rs=(String)PMS.getConfiguration().getCustomProperty("channels.raw_save");
		String nul=(String)PMS.getConfiguration().getCustomProperty("channels.navix_upload");
		String ap=(String)PMS.getConfiguration().getCustomProperty("channels.all_play");
		String pdns=(String)PMS.getConfiguration().getCustomProperty("channels.proxy_dns");
		String lsn=(String)PMS.getConfiguration().getCustomProperty("channels.long_savename");
		String os=(String)PMS.getConfiguration().getCustomProperty("channels.old_sub");
		String mp2=(String)PMS.getConfiguration().getCustomProperty("channels.mpeg2_force");
		
		if(rtmpMode!=null) {
			if(rtmpMode.trim().equalsIgnoreCase("1"))
				Channels.rtmpMethod(Channels.RTMP_MAGIC_TOKEN);
			if(rtmpMode.trim().equalsIgnoreCase("2"))
				Channels.rtmpMethod(Channels.RTMP_DUMP);
		}
		if(!ChannelUtil.empty(dbg))
			if(dbg.equalsIgnoreCase("true"))
				Channels.debug(true);
			else
				Channels.debug(false);
		if(!ChannelUtil.empty(sub))
			if(sub.equalsIgnoreCase("true"))
				Channels.setSubs(true);
			else
				Channels.setSubs(false);
		if(!ChannelUtil.empty(cacheStr))
			if(cacheStr.equalsIgnoreCase("true"))
				Channels.setCache(true);
			else
				Channels.setCache(false);
		
		// Defaults
		if(ChannelUtil.empty(rtmpPath)) {
			File plugPath=new File(PMS.getConfiguration().getMplayerPath());
			String ext=(PMS.get().isWindows()?".exe":"");
			File f=new File(plugPath.getParent()+File.separator+"rtmpdump"+ext);
			if(f.exists()&&f.canExecute())
				rtmpPath=f.getAbsolutePath();
		}
		if(ChannelUtil.empty(scriptPath)) {
			File f=new File(chPath);
			scriptPath=f.getParent()+File.separator+"scripts";
		}
		if(ChannelUtil.empty(cookiePath)) {
			cookiePath=Channels.dataPath()+File.separator+"cookies";
		}
		if(ChannelUtil.empty(credPath)) // defaults to channel path
			credPath=chPath;
		if(!ChannelUtil.empty(group)&&group.equalsIgnoreCase("true"))
			Channels.setGroup(true);
		if(!ChannelUtil.empty(mi)&&mi.equalsIgnoreCase("true"))
			Channels.setMovieInfo(true);
		if(!ChannelUtil.empty(fav)&&fav.equalsIgnoreCase("false"))
			favorite=false;
		if(!ChannelUtil.empty(nop)&&nop.equalsIgnoreCase("true"))
			noPlay=true;
		if(!ChannelUtil.empty(nd)&&nd.equalsIgnoreCase("true"))
			netDisc=true;
		if(!ChannelUtil.empty(rs)&&rs.equalsIgnoreCase("true"))
			rawSave=true;
		if(!ChannelUtil.empty(ap)&&ap.equalsIgnoreCase("false"))
			allPlay=false;
		if(!ChannelUtil.empty(pdns)) {
			if(pdns.equalsIgnoreCase("none")) {
				proxyDNS=null;
				proxyDNSMode=ChannelCfg.PROXY_DNS_NONE;
			}
			else {
				String[] tmp=pdns.split(",");
				if(tmp.length>1) {
					if(tmp[0].equalsIgnoreCase("all")) {
						proxyDNSMode=ChannelCfg.PROXY_DNS_ALL;
						Channels.setProxyDNS(tmp[1]);
					}
					if(tmp[0].equalsIgnoreCase("channel"))
						proxyDNSMode=ChannelCfg.PROXY_DNS_CHANNEL;
					proxyDNS=tmp[1];
				}
			}
		}
		if(!ChannelUtil.empty(nul)) {
			String[] tmp=nul.split(",");
			navixUploadList=tmp[0];
			navixUploadList2=null;
			if(tmp.length>1)
				navixUploadList2=tmp[1];
		}
		if(!ChannelUtil.empty(lsn)&&lsn.equalsIgnoreCase("true"))
			longSaveName=true;
		if(!ChannelUtil.empty(os)&&os.equalsIgnoreCase("true"))
			oldSub=true;
		if(!ChannelUtil.empty(mp2)&&mp2.equalsIgnoreCase("true"))
			mp2force=true;
	}

	private void configPath(String key,String val) {
		if(!ChannelUtil.empty(val))
			PMS.getConfiguration().setCustomProperty(key,val);
	}
	
	public void commit() {
		top.setSave(saPath);
		top.setPath(chPath);
		ensureCreated(saPath);
		ensureCreated(chPath);
		String dPath=chPath+File.separator+"data";
		ensureCreated(dPath);
		try {
			ChannelNaviXNookie.init(new File(dPath+File.separator+"nookie"));
			validatePMSEncoder();
			configPath("channels.path",chPath);
			configPath("channels.save",saPath);
			configPath("pmsencoder.script.directory",scriptPath);
			configPath("rtmpdump.path",rtmpPath);
			configPath("sopcast.path",sopcastPath);
			configPath("pplive.path",pplivePath);
			configPath("perl.path",perlPath);
			configPath("python.path",pythonPath);
			configPath("get-flash-videos.path",get_flPath);
			configPath("youtube-dl.path",ytPath);
			configPath("cookie.path",cookiePath);
			if(!chPath.equals(credPath))
				configPath("cred.path",credPath);
			PMS.getConfiguration().setCustomProperty("channels.debug",String.valueOf(Channels.debugStatus()));
			PMS.getConfiguration().setCustomProperty("channels.subtitles",String.valueOf(Channels.doSubs()));
			PMS.getConfiguration().setCustomProperty("channels.group_folder",String.valueOf(Channels.useGroupFolder()));
			PMS.getConfiguration().setCustomProperty("channels.favorite",String.valueOf(favorite));
			PMS.getConfiguration().setCustomProperty("channels.long_savename",String.valueOf(longSaveName));
			PMS.getConfiguration().setCustomProperty("channels.oldSub",String.valueOf(oldSub));
			PMS.getConfiguration().setCustomProperty("channels.mpeg2_force",String.valueOf(mp2force));
			if(!ChannelUtil.empty(navixUploadList)) 
				PMS.getConfiguration().setCustomProperty("channels.navix_upload",
						ChannelUtil.append(navixUploadList,",",navixUploadList2));
			PMS.getConfiguration().save();
		} catch (Exception e) {
		}
	}
	
	public void ensureCreated(String p) {
		File f=new File(p);
		if(!(f.exists()&&f.isDirectory()))
			f.mkdir();
	}
	
	private void validatePMSEncoder() throws IOException {
		setEngines();
		if(ChannelUtil.empty(scriptPath)) { 
			ensureCreated("scripts");
			scriptPath=new File("scripts").getCanonicalPath().toString();
		}
		else
			ensureCreated(scriptPath);
	}
	
	private void setEngines() {
		List<String> eng=PMS.getConfiguration().getEnginesAsList(PMS.get().getRegistry());
		for(int i=0;i<eng.size();i++) {
			if(eng.get(i).equalsIgnoreCase("pmsencoder")) // pmsencoder is there  
				return;
		}
		eng.add("pmsencoder");
		PMS.getConfiguration().setEnginesAsList((ArrayList<String>) eng);		
	}
	
	////////////////////////////////////////////
	// Fetch files
	///////////////////////////////////////////
	
	private static final String chZip="http://cloud.github.com/downloads/SharkHunter/Channel/channels.zip";
	private static final String extFile="http://cloud.github.com/downloads/SharkHunter/Channel/ext.txt";
	
	public void fetchChannels() {
		try {			
			validatePMSEncoder();
			URL u=new URL(chZip);
			URLConnection connection=u.openConnection();
			connection.setRequestProperty("User-Agent",ChannelUtil.defAgentString);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			InputStream in=connection.getInputStream();
			
			//Channels.debug("extract zip "+scriptPath);
	         ZipInputStream zis = new ZipInputStream(new BufferedInputStream(in));
	         ZipEntry entry;
	         while((entry = zis.getNextEntry()) != null) {
	          //  Channels.debug("Extracting: " +entry);
	            int count;
	            final int BUFFER = 2048;
	            byte data[] = new byte[BUFFER];
	            // write the files to the disk
	            String fName=chPath+File.separator+entry.getName();
	            if(!entry.getName().contains(".ch")) // script
	            	if(scriptPath!=null)
	            		fName=scriptPath+File.separator+entry.getName();
	            FileOutputStream fos1 = new FileOutputStream(fName);
	            BufferedOutputStream dest = new BufferedOutputStream(fos1, BUFFER);
	            while ((count = zis.read(data, 0, BUFFER)) != -1) {
	               dest.write(data, 0, count);
	            }
	            dest.flush();
	            dest.close();
	         }
	         zis.close();
	         in.close();
	      } catch(Exception e) {
	    	  Channels.debug("error fetching channels "+e);
	      }
	}
	
	private void readFile(String url,String outFile) {
		try {
			URL u=new URL(url);
			URLConnection connection=u.openConnection();
			connection.setRequestProperty("User-Agent",ChannelUtil.defAgentString);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			InputStream in=connection.getInputStream();
			File f=new File(outFile);
			FileOutputStream out=new FileOutputStream(f);
			byte[] buf = new byte[4096];
			int len;
			while((len=in.read(buf))!=-1)
				out.write(buf, 0, len);
			out.flush();
			out.close();
			in.close();
		}
		catch (Exception e) {
			Channels.debug("error fetching externals(read) "+e);
		}
	}
	
	private void fetchExternals() {
		try {
			URL u=new URL(extFile);
			URLConnection connection=u.openConnection();
			connection.setRequestProperty("User-Agent",ChannelUtil.defAgentString);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			InputStream in=connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null) {
				if(ChannelUtil.ignoreLine(line))
					continue;
				String[] str=line.split(",");
				if(str.length<3)
					continue;
				String eUrl=str[0];
				String type=str[1];
				String cfgVar=str[2];
				ensureCreated(scriptPath);
				if(type.equalsIgnoreCase("raw")) {
					String outFile=scriptPath+File.separator+cfgVar.replace(".path", "");
					readFile(eUrl,outFile);
					configPath(cfgVar,outFile);
				}					
			}
			PMS.getConfiguration().save();	
		}
		catch (Exception e) {
			Channels.debug("error fetching externals "+e);
		}
	}
	
	///////////////////////////////////////////////////
	// Channel Var functions
	///////////////////////////////////////////////////
	
	public void putChVars(String ch,String inst,String var,String val) {
		String vData=ChannelUtil.append(ch, "@", inst);
		String putData=vData+"@"+var+"@"+val;
		String vars=(String)PMS.getConfiguration().getCustomProperty("channels.ch_vars");
		if(!ChannelUtil.empty(vars)) {
			String[] varData=vars.split(",");
			String pData="";
			boolean found=false;
			for(int i=0;i<varData.length;i++) {
				if(varData[i].startsWith(vData)) {
					pData=pData+putData+",";
					found=true;
				}
				else
					pData=pData+varData[i]+",";
			}
			if(!found) // new variable
				pData=putData+","+pData;
			putData=pData;		
		}
		try {
			PMS.getConfiguration().setCustomProperty("channels.ch_vars", putData);
			PMS.getConfiguration().save();
		} catch (ConfigurationException e) {
		}
	}
	
	public void chVars(String chName,Channel ch) {
		String vars=(String)PMS.getConfiguration().getCustomProperty("channels.ch_vars");
		if(ChannelUtil.empty(vars)) // no vars
			return;
		String[] varData=vars.split(",");
		for(int i=0;i<varData.length;i++) {
			String[] var=varData[i].split("@",4);
			if(var.length<3)
				continue;
			if(!var[0].equals(chName))
				continue;
			if(var.length>3) // we got an instance
				ch.setVar(var[1],var[2],var[3]);
			else
				ch.setVar(var[1],var[2]);
		}
	}
}
