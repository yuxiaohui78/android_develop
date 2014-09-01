package cn.hugo.android.scanner.foodfinder;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import android.content.Context;

public class SSLManager {
	SSLContext  mSslContext = null;
	CertificateList cerList = new CertificateList();

	public SSLManager (){
	}

	public Certificate getCertificateFromInStream (InputStream cerIsputStream){
		Certificate ca = null;
		InputStream caInput = null;

		try {
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			caInput = new BufferedInputStream(cerIsputStream);
			ca = cf.generateCertificate(caInput);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				caInput.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return ca;
	}

	TrustManager[] getTrustManager (){
		try{
			// Create a KeyStore containing our trusted CAs
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(null, null);

			for (int i = 0; i < cerList.getCertificateCount(); i ++){
				Certificate c = getCertificateFromInStream (cerList.getCertificateInputStream(i));
				keyStore.setCertificateEntry (cerList.getCertificateName(i), c);
			}

			// Create a TrustManager that trusts the CAs in our KeyStore
			String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
			tmf.init(keyStore);
			return tmf.getTrustManagers();
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}

	public SSLContext getSSLContent (){
		
		if (mSslContext == null){
			try {
			mSslContext = SSLContext.getInstance("TLS");
			mSslContext.init(null, getTrustManager(), null);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return mSslContext;
	}
	
	public void setCertificate (String cerName, InputStream cerIs){
		cerList.addCertificate(cerName, cerIs);
	}
	
	private class CertificateList{
//		HashMap <String, InputStream> cerMap = new HashMap <String, InputStream>();
		private ArrayList<String> cerNameList = new ArrayList<String> ();
		private ArrayList<InputStream> cerInputStream = new ArrayList<InputStream> ();
		private int count = 0;
		
		public void addCertificate (String cerName, InputStream cerIs){
//			cerMap.put(cerName, cerInputStream);
			cerNameList.add(count, cerName);
			cerInputStream.add(count, cerIs);
			count ++;
		}
		
		public String getCertificateName (int index){
			return cerNameList.get(index);
		}
		
		public InputStream getCertificateInputStream (int index){
			return cerInputStream.get(index);
		}
		
		public int getCertificateCount (){
			return count;
		}
	}
}