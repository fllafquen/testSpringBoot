package com.spotifyexample.demo.service;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.miscellaneous.Device;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchAlbumsRequest;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class SpotifyService {
    private final static String CLIENTID = "1d2bc933bcdf46beaa7032b806f6ccc7";
    private final static String CLIENTSECRET = "cdf42ec0915b4c719cf3c1c8a268c91f";
    private final static URI REDIRECTURI = SpotifyHttpManager.makeUri("http://localhost:8080/callback");

    private SpotifyApi spotifyApi;

    public SpotifyService() {
        this.spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENTID)
                .setClientSecret(CLIENTSECRET)
                .setRedirectUri(REDIRECTURI)
                .build();
    }

    private Timer timer = new Timer();
    private TimerTask task  = new TimerTask() {
        @Override
        public void run() {
            try {
                System.out.println("Inside Timer Task : " + getCurrentTime().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public URI getAuthorizationUri() {
        return this.spotifyApi.authorizationCodeUri()
                .scope("user-read-private user-read-email user-read-playback-state user-modify-playback-state user-read-currently-playing")
                .show_dialog(true)
                .build()
                .execute();
    }

    public void completeAuthorizationFromCode(String code) throws IOException, SpotifyWebApiException {
        AuthorizationCodeRequest tokenRequest = spotifyApi.authorizationCode(code).build();
        AuthorizationCodeCredentials credentials = tokenRequest.execute();

        spotifyApi.setAccessToken(credentials.getAccessToken());
        spotifyApi.setRefreshToken(credentials.getRefreshToken());
    }

    public User getUserInfo() throws IOException, SpotifyWebApiException {
        return spotifyApi.getCurrentUsersProfile().build().execute();
    }

    public Device[] getAvailableDevices() throws IOException, SpotifyWebApiException {
        return spotifyApi.getUsersAvailableDevices().build().execute();
    }
    public void logout() {
        this.spotifyApi.setAccessToken(null);
        this.spotifyApi.setRefreshToken(null);
    }

    public Date getCurrentTime() throws IOException {
        String TIME_SERVER = "time-a.nist.gov";
        NTPUDPClient timeClient = new NTPUDPClient();
        InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
        TimeInfo timeInfo = timeClient.getTime(inetAddress);
        long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
        return new Date(returnTime);
    }
    
    public Paging<AlbumSimplified> album() throws IOException, SpotifyWebApiException {
    	
    	System.out.println("hi");
		return spotifyApi.searchAlbums("black album").limit(3).build().execute();
    	
    }
}
