/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pack;

import java.util.*;

/**
 *
 * @author ThidaNyein
 */
public class MyDate
{
    public String getdate()
    {
        StringBuffer str=new StringBuffer("");
        Date date=new Date();

        str.append(String.valueOf(date).substring(24));
        str.append("-");
        str.append(date.getMonth()+1);
        str.append("-");
        str.append(String.valueOf(date).substring(8,10));
        str.append(" (");
        str.append(String.valueOf(date).substring(0,3));
        str.append(")");
        return str.toString();
    }
}
