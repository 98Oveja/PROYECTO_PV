package models.products;

import java.util.ArrayList;

public class UnidadesDeMedida {
    public ArrayList Unidades()
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Metros (mts)");
        arrayList.add("Centimetros (cm)");
        arrayList.add("Litros (lts)");
        arrayList.add("Libras (lbs)");
        arrayList.add("Pies (ft)");
        arrayList.add("Yarda(yd)");
        arrayList.add("Arroba (arroba)");
        arrayList.add("Quintal (quintal)");
        arrayList.add("Galon (gal)");
        arrayList.add("Cubeta (cub)");
        arrayList.add("Onza (onz)");
        arrayList.add("Vara (vara)");
        arrayList.add("Unidad");
        return arrayList;
    }

    public ArrayList Longitud()
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Metros (mts)");
        arrayList.add("Centimetros (cm)");
        arrayList.add("Pulgadas (plg)");
        arrayList.add("Yardas (yd)");
        arrayList.add("Pies (ft)");
        arrayList.add("Vara (vara)");
        arrayList.add("Unidad");
        return arrayList;
    }

    public ArrayList Volumen()
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Galon (gal)");
        arrayList.add("Cubeta (cub)");
        arrayList.add("Litros (lts)");
        arrayList.add("Octavo de litro (1/8 lt)");
        arrayList.add("Cuarto de litro (1/4 lt)");
        arrayList.add("Unidad");
        return arrayList;
    }

    public ArrayList Peso()
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Arroba (arroba)");
        arrayList.add("Quintal (quintal)");
        arrayList.add("Libras (lbs)");
        arrayList.add("Onza (onz)");
        arrayList.add("Unidad");
        return arrayList;
    }
}