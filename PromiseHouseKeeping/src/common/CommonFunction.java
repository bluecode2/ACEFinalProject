package common;

import general.GeneralParamManager;

public class CommonFunction {
	public static String getGeneralParameterValue(String genParamId) {
		GeneralParamManager manager = new GeneralParamManager();
		String value = manager.getGeneralParamValue(genParamId);

		return value;
	}
}
