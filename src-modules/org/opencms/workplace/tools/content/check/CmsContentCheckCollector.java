/*
 * File   : $Source: /alkacon/cvs/opencms/src-modules/org/opencms/workplace/tools/content/check/CmsContentCheckCollector.java,v $
 * Date   : $Date: 2005/12/14 10:36:37 $
 * Version: $Revision: 1.1.2.3 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * Copyright (C) 2005 Alkacon Software GmbH (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software GmbH, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.workplace.tools.content.check;

import org.opencms.file.CmsObject;
import org.opencms.workplace.list.CmsListResourcesCollector;

import java.util.List;

/**
 * Collector for receiving CmsResources from a CmsContentCheckResult.<p>
 * 
 * @author Michael Emmerich
 * 
 * @version $Revision: 1.1.2.3 $ 
 * 
 * @since 6.1.2 
 */
public class CmsContentCheckCollector extends CmsListResourcesCollector {

    /** Parameter of the default collector name. */
    public static final String COLLECTOR_NAME = "checkresources";

    /** Parameter to get all resources with errors and warnings. */
    public static final String PARAM_ALL = "all";

    /** Parameter to get all resources with errors. */
    public static final String PARAM_ERROR = "error";

    /** Parameter to get all resources with  warnings. */
    public static final String PARAM_WARNING = "warning";

    /** The list of resources delivered by the collector. */
    private CmsContentCheckResult m_results;

    /**
     * Constructor, creates a new CmsContentCheckCollector.<p>
     * @param results a CmsContentCheckResult object, containing the results of the content check.
     */
    public CmsContentCheckCollector(CmsContentCheckResult results) {

        super(null);
        setDefaultCollectorName(COLLECTOR_NAME);
        setDefaultCollectorParam(PARAM_ALL);
        m_results = results;
    }

    /**
     * @see org.opencms.file.collectors.I_CmsResourceCollector#getCollectorNames()
     */
    public List getCollectorNames() {

        List names = super.getCollectorNames();
        names.add(COLLECTOR_NAME);
        return names;
    }

    /**
     * @see org.opencms.file.collectors.I_CmsResourceCollector#getResults(org.opencms.file.CmsObject)
     */
    public List getResults(CmsObject cms) {

        return getResults(cms, COLLECTOR_NAME, getDefaultCollectorParam());
    }

    /**
     * @see org.opencms.file.collectors.I_CmsResourceCollector#getResults(org.opencms.file.CmsObject, java.lang.String, java.lang.String)
     */
    public List getResults(CmsObject cms, String collectorName, String param) {

        if (param.equals(PARAM_ERROR)) {
            return m_results.getErrorResources();
        } else if (param.equals(PARAM_WARNING)) {
            return m_results.getWarningResources();
        } else if (param.equals(PARAM_ALL)) {
            return m_results.getAllResources();
        } else {
            // the default is to return all resources
            return m_results.getAllResources();
        }
    }
}
