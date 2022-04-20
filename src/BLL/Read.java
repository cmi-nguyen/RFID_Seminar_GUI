package BLL;

import com.caen.RFIDLibrary.*;

import java.util.HashMap;

public class Read {


	public static void main(String[] args) throws Exception {
	}
	/**
	 * Lấy thông tin từ tag
	 */
	public static HashMap<String,String> ReadTag() throws CAENRFIDException {
		HashMap<String,String> FreeID=new HashMap<>();
		// TODO Auto-generated method stub
		CAENRFIDReader MyReader = new CAENRFIDReader();
		try {
			MyReader.Connect(CAENRFIDPort.CAENRFID_TCP, "192.168.1.2"); 
	        CAENRFIDLogicalSource MySource = MyReader.GetSource("Source_0");
	        
	      //get Reader Infor
	      CAENRFIDReaderInfo Info = MyReader.GetReaderInfo();
	      String Model = Info.GetModel();
	      String SerialNumber = Info.GetSerialNumber();
	      String FWRelease = MyReader.GetFirmwareRelease();
	      int power = MyReader.GetPower();

	      System.out.println("Model: "+Model);
	      System.out.println("SerialNumber: "+SerialNumber);
	      System.out.println("FWRelease: "+FWRelease);
	      System.out.println("power: "+power);
	      
	      System.out.println("");
	      	MySource.SetSession_EPC_C1G2(CAENRFIDLogicalSourceConstants.EPC_C1G2_SESSION_S1);

	        CAENRFIDTag[] MyTags = MySource.InventoryTag();
	       
	        if (MyTags.length > 0)
	        {
	            for (int i = 0; i < MyTags.length; i++)
	            {
					FreeID.put(hex(MyTags[i].GetId()) ,"");
	                System.out.println("EPC: "+ hex(MyTags[i].GetId())  + " | Antenna : " +MyTags[i].GetAntenna() +" | TID:"+ (MyTags[i].GetTID()) +" | RSSI : "+Integer.valueOf(MyTags[i].GetRSSI()));
	            }
	        }
	        
	    
	        MyReader.Disconnect();
		}catch(Exception ex) {
			System.out.println(ex);
			if(MyReader != null)MyReader.Disconnect();
		}
        return FreeID;
	}
	
	public static String hex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte aByte : bytes) {
			result.append(String.format("%02x", aByte));
			// upper case
			// result.append(String.format("%02X", aByte));
		}
		return result.toString().toUpperCase();
	}

}
