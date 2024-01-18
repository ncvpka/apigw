package vn.supperapp.apigw.clients.firebase.objs;

public enum FirebaseDynamicLinkSuffixType {

    SHORT(-1, "SHORT"),
    UNGUESSABLE(1, "UNGUESSABLE");;

    private final int value;
    private final String code;

    private FirebaseDynamicLinkSuffixType(int value, String code) {
        this.value = value;
        this.code = code;
    }

    public int value() {
        return this.value;
    }

    public String code() {
        return this.code;
    }

    public boolean is(int value) {
        return this.value == value;
    }

}
