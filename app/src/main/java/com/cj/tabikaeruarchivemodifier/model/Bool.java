package com.cj.tabikaeruarchivemodifier.model;

import com.cj.tabikaeruarchivemodifier.model.DataList.ElementFactory;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Bool extends SimpleData<Void, Boolean> {
    Bool(RandomAccessFile r) throws IOException {
        super(r, null);
    }

    @Override
    protected void initialize(Void arg) throws IOException {
        this.value = r.readBoolean();
    }

    @Override
    public boolean save() {
        if (modified) {
            try {
                r.seek(offset());
                r.writeBoolean(value);
                modified = false;
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean write(RandomAccessFile r) {
        try {
            r.writeBoolean(value);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static class BoolElementFactory implements ElementFactory<Bool> {
        @Override
        public Bool create(RandomAccessFile r) throws IOException {
            return new Bool(r);
        }
    }
}
