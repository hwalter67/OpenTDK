package org.opentdk.api.datastorage;

import java.io.IOException;

import org.opentdk.api.datastorage.BaseContainer.EContainerFormat;

public class YAMLDataContainer implements CustomContainer {

	/**
	 * An instance of the DataContainer that should be filled with the data from the
	 * connected source file. -> Task of the specific data containers.
	 */
	private final DataContainer dc;

	/**
	 * Construct a new specific <code>DataContainer</code> for JAML files.
	 *
	 * @param dCont the <code>DataContainer</code> instance to use it in the read
	 *              and write methods of this specific data container
	 */
	YAMLDataContainer(DataContainer dCont) {
		dc = dCont;
		dc.containerFormat = EContainerFormat.YAML;
	}

	@Override
	public void readData(Filter filter) {
		
		
	}

	@Override
	public void writeData(String srcFileName) throws IOException {
		
		
	}

	@Override
	public String asString() {
		
		return null;
	}


}
