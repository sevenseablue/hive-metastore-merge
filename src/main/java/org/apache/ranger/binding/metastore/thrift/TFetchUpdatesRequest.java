/**
 * Autogenerated by Thrift Compiler (0.9.3)
 * <p>
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */
package org.apache.ranger.binding.metastore.thrift;

import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

import javax.annotation.Generated;
import java.util.*;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2017-04-01")
public class TFetchUpdatesRequest implements org.apache.thrift.TBase<TFetchUpdatesRequest, TFetchUpdatesRequest._Fields>, java.io.Serializable, Cloneable, Comparable<TFetchUpdatesRequest> {
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TFetchUpdatesRequest");
    private static final org.apache.thrift.protocol.TField PROTOCOL_VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("protocol_version", org.apache.thrift.protocol.TType.I32, (short) 1);
    private static final org.apache.thrift.protocol.TField START_VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("start_version", org.apache.thrift.protocol.TType.I64, (short) 2);
    private static final org.apache.thrift.protocol.TField END_VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("end_version", org.apache.thrift.protocol.TType.I64, (short) 3);
    private static final org.apache.thrift.protocol.TField SERVER_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("server_name", org.apache.thrift.protocol.TType.STRING, (short) 4);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    // isset id assignments
    private static final int __START_VERSION_ISSET_ID = 0;
    private static final int __END_VERSION_ISSET_ID = 1;

    static {
        schemes.put(StandardScheme.class, new TFetchUpdatesRequestStandardSchemeFactory());
        schemes.put(TupleScheme.class, new TFetchUpdatesRequestTupleSchemeFactory());
    }

    static {
        Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
        tmpMap.put(_Fields.PROTOCOL_VERSION, new org.apache.thrift.meta_data.FieldMetaData("protocol_version", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, MetaStoreUpdateServiceVersion.class)));
        tmpMap.put(_Fields.START_VERSION, new org.apache.thrift.meta_data.FieldMetaData("start_version", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
        tmpMap.put(_Fields.END_VERSION, new org.apache.thrift.meta_data.FieldMetaData("end_version", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
        tmpMap.put(_Fields.SERVER_NAME, new org.apache.thrift.meta_data.FieldMetaData("server_name", org.apache.thrift.TFieldRequirementType.REQUIRED,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TFetchUpdatesRequest.class, metaDataMap);
    }

    /**
     *
     * @see MetaStoreUpdateServiceVersion
     */
    public MetaStoreUpdateServiceVersion protocol_version; // required
    public long start_version; // required
    public long end_version; // required
    public String server_name; // required
    private byte __isset_bitfield = 0;

    public TFetchUpdatesRequest() {
        this.protocol_version = MetaStoreUpdateServiceVersion.V1;

    }

    public TFetchUpdatesRequest(
            MetaStoreUpdateServiceVersion protocol_version,
            long start_version,
            long end_version,
            String server_name) {
        this();
        this.protocol_version = protocol_version;
        this.start_version = start_version;
        setStart_versionIsSet(true);
        this.end_version = end_version;
        setEnd_versionIsSet(true);
        this.server_name = server_name;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public TFetchUpdatesRequest(TFetchUpdatesRequest other) {
        __isset_bitfield = other.__isset_bitfield;
        if (other.isSetProtocol_version()) {
            this.protocol_version = other.protocol_version;
        }
        this.start_version = other.start_version;
        this.end_version = other.end_version;
        if (other.isSetServer_name()) {
            this.server_name = other.server_name;
        }
    }

    public TFetchUpdatesRequest deepCopy() {
        return new TFetchUpdatesRequest(this);
    }

    @Override
    public void clear() {
        this.protocol_version = MetaStoreUpdateServiceVersion.V1;

        setStart_versionIsSet(false);
        this.start_version = 0;
        setEnd_versionIsSet(false);
        this.end_version = 0;
        this.server_name = null;
    }

    /**
     *
     * @see MetaStoreUpdateServiceVersion
     */
    public MetaStoreUpdateServiceVersion getProtocol_version() {
        return this.protocol_version;
    }

    /**
     *
     * @see MetaStoreUpdateServiceVersion
     */
    public TFetchUpdatesRequest setProtocol_version(MetaStoreUpdateServiceVersion protocol_version) {
        this.protocol_version = protocol_version;
        return this;
    }

    public void unsetProtocol_version() {
        this.protocol_version = null;
    }

    /** Returns true if field protocol_version is set (has been assigned a value) and false otherwise */
    public boolean isSetProtocol_version() {
        return this.protocol_version != null;
    }

    public void setProtocol_versionIsSet(boolean value) {
        if (!value) {
            this.protocol_version = null;
        }
    }

    public long getStart_version() {
        return this.start_version;
    }

    public TFetchUpdatesRequest setStart_version(long start_version) {
        this.start_version = start_version;
        setStart_versionIsSet(true);
        return this;
    }

    public void unsetStart_version() {
        __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __START_VERSION_ISSET_ID);
    }

    /** Returns true if field start_version is set (has been assigned a value) and false otherwise */
    public boolean isSetStart_version() {
        return EncodingUtils.testBit(__isset_bitfield, __START_VERSION_ISSET_ID);
    }

    public void setStart_versionIsSet(boolean value) {
        __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __START_VERSION_ISSET_ID, value);
    }

    public long getEnd_version() {
        return this.end_version;
    }

    public TFetchUpdatesRequest setEnd_version(long end_version) {
        this.end_version = end_version;
        setEnd_versionIsSet(true);
        return this;
    }

    public void unsetEnd_version() {
        __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __END_VERSION_ISSET_ID);
    }

    /** Returns true if field end_version is set (has been assigned a value) and false otherwise */
    public boolean isSetEnd_version() {
        return EncodingUtils.testBit(__isset_bitfield, __END_VERSION_ISSET_ID);
    }

    public void setEnd_versionIsSet(boolean value) {
        __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __END_VERSION_ISSET_ID, value);
    }

    public String getServer_name() {
        return this.server_name;
    }

    public TFetchUpdatesRequest setServer_name(String server_name) {
        this.server_name = server_name;
        return this;
    }

    public void unsetServer_name() {
        this.server_name = null;
    }

    /** Returns true if field server_name is set (has been assigned a value) and false otherwise */
    public boolean isSetServer_name() {
        return this.server_name != null;
    }

    public void setServer_nameIsSet(boolean value) {
        if (!value) {
            this.server_name = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (field) {
            case PROTOCOL_VERSION:
                if (value == null) {
                    unsetProtocol_version();
                } else {
                    setProtocol_version((MetaStoreUpdateServiceVersion) value);
                }
                break;

            case START_VERSION:
                if (value == null) {
                    unsetStart_version();
                } else {
                    setStart_version((Long) value);
                }
                break;

            case END_VERSION:
                if (value == null) {
                    unsetEnd_version();
                } else {
                    setEnd_version((Long) value);
                }
                break;

            case SERVER_NAME:
                if (value == null) {
                    unsetServer_name();
                } else {
                    setServer_name((String) value);
                }
                break;

        }
    }

    public Object getFieldValue(_Fields field) {
        switch (field) {
            case PROTOCOL_VERSION:
                return getProtocol_version();

            case START_VERSION:
                return getStart_version();

            case END_VERSION:
                return getEnd_version();

            case SERVER_NAME:
                return getServer_name();

        }
        throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }

        switch (field) {
            case PROTOCOL_VERSION:
                return isSetProtocol_version();
            case START_VERSION:
                return isSetStart_version();
            case END_VERSION:
                return isSetEnd_version();
            case SERVER_NAME:
                return isSetServer_name();
        }
        throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
        if (that == null)
            return false;
        if (that instanceof TFetchUpdatesRequest)
            return this.equals((TFetchUpdatesRequest) that);
        return false;
    }

    public boolean equals(TFetchUpdatesRequest that) {
        if (that == null)
            return false;

        boolean this_present_protocol_version = true && this.isSetProtocol_version();
        boolean that_present_protocol_version = true && that.isSetProtocol_version();
        if (this_present_protocol_version || that_present_protocol_version) {
            if (!(this_present_protocol_version && that_present_protocol_version))
                return false;
            if (!this.protocol_version.equals(that.protocol_version))
                return false;
        }

        boolean this_present_start_version = true;
        boolean that_present_start_version = true;
        if (this_present_start_version || that_present_start_version) {
            if (!(this_present_start_version && that_present_start_version))
                return false;
            if (this.start_version != that.start_version)
                return false;
        }

        boolean this_present_end_version = true;
        boolean that_present_end_version = true;
        if (this_present_end_version || that_present_end_version) {
            if (!(this_present_end_version && that_present_end_version))
                return false;
            if (this.end_version != that.end_version)
                return false;
        }

        boolean this_present_server_name = true && this.isSetServer_name();
        boolean that_present_server_name = true && that.isSetServer_name();
        if (this_present_server_name || that_present_server_name) {
            if (!(this_present_server_name && that_present_server_name))
                return false;
            if (!this.server_name.equals(that.server_name))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        List<Object> list = new ArrayList<Object>();

        boolean present_protocol_version = true && (isSetProtocol_version());
        list.add(present_protocol_version);
        if (present_protocol_version)
            list.add(protocol_version.getValue());

        boolean present_start_version = true;
        list.add(present_start_version);
        if (present_start_version)
            list.add(start_version);

        boolean present_end_version = true;
        list.add(present_end_version);
        if (present_end_version)
            list.add(end_version);

        boolean present_server_name = true && (isSetServer_name());
        list.add(present_server_name);
        if (present_server_name)
            list.add(server_name);

        return list.hashCode();
    }

    @Override
    public int compareTo(TFetchUpdatesRequest other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }

        int lastComparison = 0;

        lastComparison = Boolean.valueOf(isSetProtocol_version()).compareTo(other.isSetProtocol_version());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetProtocol_version()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.protocol_version, other.protocol_version);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetStart_version()).compareTo(other.isSetStart_version());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetStart_version()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.start_version, other.start_version);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetEnd_version()).compareTo(other.isSetEnd_version());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetEnd_version()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.end_version, other.end_version);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetServer_name()).compareTo(other.isSetServer_name());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetServer_name()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.server_name, other.server_name);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        return 0;
    }

    public _Fields fieldForId(int fieldId) {
        return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
        schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
        schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TFetchUpdatesRequest(");
        boolean first = true;

        sb.append("protocol_version:");
        if (this.protocol_version == null) {
            sb.append("null");
        } else {
            sb.append(this.protocol_version);
        }
        first = false;
        if (!first) sb.append(", ");
        sb.append("start_version:");
        sb.append(this.start_version);
        first = false;
        if (!first) sb.append(", ");
        sb.append("end_version:");
        sb.append(this.end_version);
        first = false;
        if (!first) sb.append(", ");
        sb.append("server_name:");
        if (this.server_name == null) {
            sb.append("null");
        } else {
            sb.append(this.server_name);
        }
        first = false;
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        // check for required fields
        if (protocol_version == null) {
            throw new TProtocolException("Required field 'protocol_version' was not present! Struct: " + toString());
        }
        // alas, we cannot check 'start_version' because it's a primitive and you chose the non-beans generator.
        // alas, we cannot check 'end_version' because it's a primitive and you chose the non-beans generator.
        if (server_name == null) {
            throw new TProtocolException("Required field 'server_name' was not present! Struct: " + toString());
        }
        // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        try {
            write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
        } catch (TException te) {
            throw new java.io.IOException(te);
        }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        try {
            // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
            __isset_bitfield = 0;
            read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
        } catch (TException te) {
            throw new java.io.IOException(te);
        }
    }

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
        /**
         *
         * @see MetaStoreUpdateServiceVersion
         */
        PROTOCOL_VERSION((short) 1, "protocol_version"),
        START_VERSION((short) 2, "start_version"),
        END_VERSION((short) 3, "end_version"),
        SERVER_NAME((short) 4, "server_name");

        private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

        static {
            for (_Fields field : EnumSet.allOf(_Fields.class)) {
                byName.put(field.getFieldName(), field);
            }
        }

        private final short _thriftId;
        private final String _fieldName;

        _Fields(short thriftId, String fieldName) {
            _thriftId = thriftId;
            _fieldName = fieldName;
        }

        /**
         * Find the _Fields constant that matches fieldId, or null if its not found.
         */
        public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
                case 1: // PROTOCOL_VERSION
                    return PROTOCOL_VERSION;
                case 2: // START_VERSION
                    return START_VERSION;
                case 3: // END_VERSION
                    return END_VERSION;
                case 4: // SERVER_NAME
                    return SERVER_NAME;
                default:
                    return null;
            }
        }

        /**
         * Find the _Fields constant that matches fieldId, throwing an exception
         * if it is not found.
         */
        public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            return fields;
        }

        /**
         * Find the _Fields constant that matches name, or null if its not found.
         */
        public static _Fields findByName(String name) {
            return byName.get(name);
        }

        public short getThriftFieldId() {
            return _thriftId;
        }

        public String getFieldName() {
            return _fieldName;
        }
    }

    private static class TFetchUpdatesRequestStandardSchemeFactory implements SchemeFactory {
        public TFetchUpdatesRequestStandardScheme getScheme() {
            return new TFetchUpdatesRequestStandardScheme();
        }
    }

    private static class TFetchUpdatesRequestStandardScheme extends StandardScheme<TFetchUpdatesRequest> {

        public void read(org.apache.thrift.protocol.TProtocol iprot, TFetchUpdatesRequest struct) throws TException {
            org.apache.thrift.protocol.TField schemeField;
            iprot.readStructBegin();
            while (true) {
                schemeField = iprot.readFieldBegin();
                if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                    break;
                }
                switch (schemeField.id) {
                    case 1: // PROTOCOL_VERSION
                        if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
                            struct.protocol_version = MetaStoreUpdateServiceVersion.findByValue(iprot.readI32());
                            struct.setProtocol_versionIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 2: // START_VERSION
                        if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
                            struct.start_version = iprot.readI64();
                            struct.setStart_versionIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 3: // END_VERSION
                        if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
                            struct.end_version = iprot.readI64();
                            struct.setEnd_versionIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 4: // SERVER_NAME
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.server_name = iprot.readString();
                            struct.setServer_nameIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    default:
                        org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                }
                iprot.readFieldEnd();
            }
            iprot.readStructEnd();

            // check for required fields of primitive type, which can't be checked in the validate method
            if (!struct.isSetStart_version()) {
                throw new TProtocolException("Required field 'start_version' was not found in serialized data! Struct: " + toString());
            }
            if (!struct.isSetEnd_version()) {
                throw new TProtocolException("Required field 'end_version' was not found in serialized data! Struct: " + toString());
            }
            struct.validate();
        }

        public void write(org.apache.thrift.protocol.TProtocol oprot, TFetchUpdatesRequest struct) throws TException {
            struct.validate();

            oprot.writeStructBegin(STRUCT_DESC);
            if (struct.protocol_version != null) {
                oprot.writeFieldBegin(PROTOCOL_VERSION_FIELD_DESC);
                oprot.writeI32(struct.protocol_version.getValue());
                oprot.writeFieldEnd();
            }
            oprot.writeFieldBegin(START_VERSION_FIELD_DESC);
            oprot.writeI64(struct.start_version);
            oprot.writeFieldEnd();
            oprot.writeFieldBegin(END_VERSION_FIELD_DESC);
            oprot.writeI64(struct.end_version);
            oprot.writeFieldEnd();
            if (struct.server_name != null) {
                oprot.writeFieldBegin(SERVER_NAME_FIELD_DESC);
                oprot.writeString(struct.server_name);
                oprot.writeFieldEnd();
            }
            oprot.writeFieldStop();
            oprot.writeStructEnd();
        }

    }

    private static class TFetchUpdatesRequestTupleSchemeFactory implements SchemeFactory {
        public TFetchUpdatesRequestTupleScheme getScheme() {
            return new TFetchUpdatesRequestTupleScheme();
        }
    }

    private static class TFetchUpdatesRequestTupleScheme extends TupleScheme<TFetchUpdatesRequest> {

        @Override
        public void write(org.apache.thrift.protocol.TProtocol prot, TFetchUpdatesRequest struct) throws TException {
            TTupleProtocol oprot = (TTupleProtocol) prot;
            oprot.writeI32(struct.protocol_version.getValue());
            oprot.writeI64(struct.start_version);
            oprot.writeI64(struct.end_version);
            oprot.writeString(struct.server_name);
        }

        @Override
        public void read(org.apache.thrift.protocol.TProtocol prot, TFetchUpdatesRequest struct) throws TException {
            TTupleProtocol iprot = (TTupleProtocol) prot;
            struct.protocol_version = MetaStoreUpdateServiceVersion.findByValue(iprot.readI32());
            struct.setProtocol_versionIsSet(true);
            struct.start_version = iprot.readI64();
            struct.setStart_versionIsSet(true);
            struct.end_version = iprot.readI64();
            struct.setEnd_versionIsSet(true);
            struct.server_name = iprot.readString();
            struct.setServer_nameIsSet(true);
        }
    }

}

