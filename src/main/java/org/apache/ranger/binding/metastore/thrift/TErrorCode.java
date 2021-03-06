/**
 * Autogenerated by Thrift Compiler (0.9.3)
 * <p>
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */
package org.apache.ranger.binding.metastore.thrift;


import org.apache.thrift.TEnum;

public enum TErrorCode implements TEnum {
    OK(0),
    ERROR(1),
    INVALID(2);

    private final int value;

    private TErrorCode(int value) {
        this.value = value;
    }

    /**
     * Find a the enum type by its integer value, as defined in the Thrift IDL.
     * @return null if the value is not found.
     */
    public static TErrorCode findByValue(int value) {
        switch (value) {
            case 0:
                return OK;
            case 1:
                return ERROR;
            case 2:
                return INVALID;
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
