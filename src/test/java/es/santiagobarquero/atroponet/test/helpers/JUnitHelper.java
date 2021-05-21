package es.santiagobarquero.atroponet.test.helpers;

import java.util.List;

public interface JUnitHelper<E, DVO> {

	E getMockedObjectEntity(final boolean lazy);
	DVO getMockedObjectDvo(final boolean lazy);
	List<E> getMockedListEntity(final boolean lazy, final int size);
	List<DVO> getMockedListDvo(final boolean lazy, final int size);
	
}
