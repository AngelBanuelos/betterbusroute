/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdegree.ada.sort;

/**
 *
 * @author Angel.Sahagun
 */
public interface Sortable {

    @Override
    public boolean equals(Object obj);

    public int getIndex();

    public void setIndex(int index);

    public int getValue();
}
