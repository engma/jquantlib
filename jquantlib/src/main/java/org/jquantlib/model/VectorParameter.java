///*
//Copyright (C) 2009 Praneet Tiwari
//
//This source code is release under the BSD License.
//
//This file is part of JQuantLib, a free-software/open-source library
//for financial quantitative analysts and developers - http://jquantlib.org/
//
//JQuantLib is free software: you can redistribute it and/or modify it
//under the terms of the JQuantLib license.  You should have received a
//copy of the license along with this program; if not, please email
//<jquant-devel@lists.sourceforge.net>. The license is also available online at
//<http://www.jquantlib.org/index.php/LICENSE.TXT>.
//
//This program is distributed in the hope that it will be useful, but WITHOUT
//ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
//FOR A PARTICULAR PURPOSE.  See the license for more details.
//
//JQuantLib is based on QuantLib. http://quantlib.org/
//When applicable, the original copyright notice follows this notice.
// */
//
//package org.jquantlib.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Container for vector parameter (time series) with interpolation function
// *
// * @author Masakatsu Wakayu
// */
//public abstract class VectorParameter {
//
//    //
//    // protected fields
//    //
//
//	protected String name;
//	protected List<VectorItem> valueList;
//
//    /**
//     *  Empty constructor
//     *
//     * @param serialNumber
//     * @return
//     */
//    public VectorParameter(String name)
//    {
//        this.name = name;
//        valueList = new ArrayList<VectorItem>();
//    }
//    
//    //
//    // public methods
//    //
//
//    /**
//     *  Replace "null" entries by interpolation rules as defined in the subclass.
//     *
//     * @param
//     * @return
//     */
//    public abstract void FillValues();
//
//    // Returns interpolated datacolumn parameter correponding to the expiry (date or date string)
//    /**
//     *  Computes the value corresponding to the given date.
//     *
//     * @param
//     * @return interpolated datacolumn parameter correponding to the expiry (date or date string)
//     */
//    public abstract double Value(Date expiry);
//
//    public VectorItem Get(Date expiry)
//    {
//        List<VectorItem> exactmatch = 
//        		
//        		
//        		ValueList.Where(x => x.Maturity.CompareTo(expiry) == 0).ToList();
//
//        if (exactmatch == null || exactmatch.Count() == 0)
//        {
//            return null;
//        }
//
//        else if (exactmatch.Count() == 1)
//        {
//            return exactmatch.ElementAt(0);
//        }
//
//        else
//        {
//            throw new IndexOutOfRangeException("Duplicate vector entries : " + this.Name);
//        }
//    }
//
//    public bool Add(IVectorItem item, bool overwrite = true)
//    {
//        if (this.IsContinuous != item.IsContinuous)
//        {
//            throw new InvalidOperationException("Cannot mix continuous and discrete parameters");
//        }
//
//        List<IVectorItem> exactmatch = ValueList.Where(x => x.Maturity.CompareTo(item.Maturity) == 0).ToList();
//
//        if (exactmatch == null || exactmatch.Count() == 0)
//        {
//            ValueList.Add(item);
//            return true;
//        }
//        else
//        {
//            if (overwrite)
//            {
//                exactmatch.ForEach(x => ValueList.Remove(x));
//                ValueList.Add(item);
//                return true;
//            }
//            else
//            {
//                return false;
//            }
//        }
//    }
//    public bool Add(IDateParameter maturity, object value = null, bool isimplied = false, bool overwrite = true,
//        string valueType = null, Action<IVectorItem> defaultsetting = null)
//    {
//        IVectorItem newitem;
//
//        if (IsContinuous)
//        {
//            newitem = new ContinuousVectorItem(maturity, (double?)value, isimplied, (valueType == null ? this.ValueType : valueType));
//        }
//        else
//        {
//            newitem = new DiscreteVectorItem(maturity, (double?)value, isimplied, (valueType == null ? this.ValueType : valueType));
//        }
//
//        if (defaultsetting != null)
//        {
//            defaultsetting(newitem);
//        }
//        return Add(newitem, overwrite);
//    }
//
//    public void FillDates(string periodicity, DateTime valuedate, IDateParameter enddate)
//    {
//        string skipunit = periodicity.Substring(periodicity.Length - 1, 1);
//        int skiprange;
//        if (!int.TryParse(periodicity.Substring(0, periodicity.Length - 1), out skiprange))
//        {
//            throw new FormatException("Periodicity not recognised : " + periodicity);
//        }
//
//        int i = 0;
//        DateTime currentdate = valuedate;
//        while (currentdate <= enddate.Date)
//        {
//            Add(new DateParameter(DateServices.TimeSpanToString(valuedate, currentdate), valuedate), null, true, false);
//            i++;
//            currentdate = DateServices.ObjectToDate(skiprange * i + skipunit, valuedate);
//        }
//    }
//
//    public void Clear()
//    {
//        ValueList.Clear();
//    }
//
//    public void ForEach(Action<IVectorItem> action)
//    {
//        foreach (IVectorItem v in ValueList)
//        {
//            action(v);
//        }
//    }
//
//}
//
//
