/**
 * Autogenerated by Thrift Compiler (0.9.3)
 * <p>
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */
package org.apache.ranger.binding.metastore.thrift;


import org.apache.thrift.TEnum;

public enum MetaStoreUpdateServiceVersion implements org.apache.thrift.TEnum {
    V1(0);

    private final int value;

    private MetaStoreUpdateServiceVersion(int value) {
        this.value = value;
    }

    /**
     * Find a the enum type by its integer value, as defined in the Thrift IDL.
     * @return null if the value is not found.
     */
    public static MetaStoreUpdateServiceVersion findByValue(int value) {
        switch (value) {
            case 0:
                return V1;
            default:
                return null;
        }
    }

    /**
     * Get the integer value of this enum value, as defined in the Thrift IDL.
     */
    public int getValue() {
        return value;
    }
}
