package com.cj.tabikaeruarchivemodifier.model;

import android.support.annotation.Nullable;

import com.cj.tabikaeruarchivemodifier.model.Bool.BoolElementFactory;
import com.cj.tabikaeruarchivemodifier.model.Clover.CloverElementFactory;
import com.cj.tabikaeruarchivemodifier.model.Event.EventElementFactory;
import com.cj.tabikaeruarchivemodifier.model.GameData.OnLoadedListener;
import com.cj.tabikaeruarchivemodifier.model.Int.IntElementFactory;
import com.cj.tabikaeruarchivemodifier.model.Item.ItemElementFactory;
import com.cj.tabikaeruarchivemodifier.model.Mail.MailElementFactory;
import com.cj.tabikaeruarchivemodifier.utils.Constants;
import com.cj.tabikaeruarchivemodifier.utils.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

@SuppressWarnings("unused")
public final class GameData extends Data<OnLoadedListener> implements Constants {
    private int version;
    private int versionStart;
    private int supportID;
    private int iapCallBackCnt;

    private DataList<Bool> hoten;
    private Int clover;
    private Int ticket;
    private DataList<Clover> cloverList;
    private DateTime lastDateTime;
    private Int mailListNextId;
    private DataList<Mail> mailList;
    private DataList<Item> itemList;
    private DataList<Int> bagList;
    private DataList<Int> deskList;
    private DataList<Int> bagListVirtual;
    private DataList<Int> deskListVirtual;
    private DataList<Bool> collectFlags;
    private DataList<Int> collectFailedCnt;
    private DataList<Bool> specialtyFlags;
    private DataList<Event> eventTimerList;
    private DataList<Event> eventActiveList;
    private Int tutorialStep;
    private DataList<Bool> firstFlag;
    private Str frogName;
    private Int frogAchieveId;
    private DataList<Bool> achieveFlags;
    private Int frogMotion;
    private Bool home;
    private Bool drift;
    private Int restTime;
    private Int lastTravelTime;
    private Bool standby;
    private Int standbyWait;
    private Int bgmVolume;
    private Int seVolume;
    private Bool noticeFlag;
    private DataList<Int> gameFlags;
    private Int tmpRaffleResult;

    private GameData(File archive, OnLoadedListener listener) throws IOException {
        super(new RandomAccessFile(archive, "rwd"), listener);
    }

    @Override
    protected void initialize(OnLoadedListener listener) throws IOException {
        r.seek(offset());
        version = r.readInt();

        if (version < 10400) {
            throw new UnsupportedOperationException("Unsupported Game Archive File");
        }

        supportID = r.readInt();
        hoten = new DataList<>(r, new BoolElementFactory());
        clover = new Int(r);
        ticket = new Int(r);
        cloverList = new DataList<>(r, new CloverElementFactory());
        lastDateTime = new DateTime(r);
        mailListNextId = new Int(r);
        mailList = new DataList<>(r, new MailElementFactory());
        itemList = new DataList<>(r, new ItemElementFactory());
        bagList = new DataList<>(r, new IntElementFactory());
        deskList = new DataList<>(r, new IntElementFactory());
        bagListVirtual = new DataList<>(r, new IntElementFactory());
        deskListVirtual = new DataList<>(r, new IntElementFactory());
        collectFlags = new DataList<>(r, new BoolElementFactory());
        collectFailedCnt = new DataList<>(r, new IntElementFactory());
        specialtyFlags = new DataList<>(r, new BoolElementFactory());
        eventTimerList = new DataList<>(r, new EventElementFactory());
        eventActiveList = new DataList<>(r, new EventElementFactory());
        tutorialStep = new Int(r);
        firstFlag = new DataList<>(r, new BoolElementFactory());
        frogName = new Str(r, 0x14);
        frogAchieveId = new Int(r);
        achieveFlags = new DataList<>(r, new BoolElementFactory());
        frogMotion = new Int(r);
        home = new Bool(r);
        drift = new Bool(r);
        restTime = new Int(r);
        lastTravelTime = new Int(r);
        standby = new Bool(r);
        standbyWait = new Int(r);
        bgmVolume = new Int(r);
        seVolume = new Int(r);
        noticeFlag = new Bool(r);
        gameFlags = new DataList<>(r, new IntElementFactory());
        tmpRaffleResult = new Int(r);
        versionStart = r.readInt();

        if (version >= 10500) {
            iapCallBackCnt = r.readInt();
        }

        if (listener != null) {
            listener.onLoaded(this);
        }
    }

    @Nullable
    public static GameData load(File archive, OnLoadedListener listener) {
        try {
            return new GameData(archive, listener);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void reload(OnLoadedListener listener) {
        try {
            initialize(listener);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        Util.closeQuietly(r);
    }

    public float version() {
        return version;
    }

    public int supportID() {
        return supportID;
    }

    public Int clover() {
        return clover;
    }

    public Int ticket() {
        return ticket;
    }

    public DateTime lastDateTime() {
        return lastDateTime;
    }

    public List<Mail> mailList() {
        return mailList.data();
    }

    public List<Item> itemList() {
        return itemList.data();
    }

    public List<Int> bagList() {
        return bagList.data();
    }

    public List<Int> deskList() {
        return deskList.data();
    }

    public List<Int> bagListVirtual() {
        return bagListVirtual.data();
    }

    public List<Int> deskListVirtual() {
        return deskListVirtual.data();
    }

    public List<Bool> collectFlags() {
        return collectFlags.data();
    }

    public List<Int> collectFailedCnt() {
        return collectFailedCnt.data();
    }

    public List<Bool> specialtyFlags() {
        return specialtyFlags.data();
    }

    public List<Event> eventTimerList() {
        return eventTimerList.data();
    }

    public List<Event> eventActiveList() {
        return eventActiveList.data();
    }

    public Int tutorialStep() {
        return tutorialStep;
    }

    public List<Bool> firstFlag() {
        return firstFlag.data();
    }

    public Str frogName() {
        return frogName;
    }

    public Int frogAchieveId() {
        return frogAchieveId;
    }

    public List<Bool> achieveFlags() {
        return achieveFlags.data();
    }

    public Int frogMotion() {
        return frogMotion;
    }

    public Bool home() {
        return home;
    }

    public Bool drift() {
        return drift;
    }

    public Int restTime() {
        return restTime;
    }

    public Int lastTravelTime() {
        return lastTravelTime;
    }

    public Bool standby() {
        return standby;
    }

    public Int standbyWait() {
        return standbyWait;
    }

    public Int bgmVolume() {
        return bgmVolume;
    }

    public Int seVolume() {
        return seVolume;
    }

    public Bool noticeFlag() {
        return noticeFlag;
    }

    public List<Int> gameFlags() {
        return gameFlags.data();
    }

    public Int tmpRaffleResult() {
        return tmpRaffleResult;
    }

    public float versionStart() {
        return versionStart;
    }

    public boolean getAllItem(OnLoadedListener listener) {
        int len = 0x21c - itemList.length();
        if (len > 0) {
            try {
                expandData(itemList, len);
                writeItems();
                reload(listener);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            for (Item item : itemList) {
                if (!item.stock(Item.MAX_STOCK).save()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void writeItems() throws IOException {
        r.seek(itemList.offset());
        r.writeInt(ALL_ITEMS_ARRAY.length);
        for (int i : ALL_ITEMS_ARRAY) {
            r.writeInt((i >>> 8) & 0xffff);
            r.writeInt(i & 0xff);
        }
    }

    private void expandData(Data data, int len) throws IOException {
        int tail = (int) (data.offset() + data.length());
        byte[] buffer = new byte[(int) (r.length() - tail)];
        r.seek(tail);
        r.readFully(buffer);
        r.setLength(r.length() + len);
        r.seek(tail + len);
        r.write(buffer);
    }

    public boolean haveAllAchieve() {
        return checkFlags(achieveFlags, ACHIEVE_FLAGS_BITS, ACHIEVE_FLAGS_BITS_LEN);
    }

    public boolean haveAllCollect() {
        return checkFlags(collectFlags, COLLECT_FLAGS_BITS, COLLECT_FLAGS_BITS_LEN);
    }

    public boolean haveAllSpecialty() {
        return checkFlags(specialtyFlags, SPECIALTY_FLAGS_BITS, SPECIALTY_FLAGS_BITS_LEN);
    }

    public boolean checkFlags(DataList<Bool> flags, long flagBits, int len) {
        for (int i = 0; i < len; i++) {
            if (((flagBits >>> i) & 1) == 1 && !flags.get(i).value()) {
                return false;
            }
        }
        return true;
    }

    public boolean getAllAchieve() {
        return setFlags(achieveFlags, ACHIEVE_FLAGS_BITS, ACHIEVE_FLAGS_BITS_LEN);
    }

    public boolean getAllCollect() {
        return setFlags(collectFlags, COLLECT_FLAGS_BITS, COLLECT_FLAGS_BITS_LEN);
    }

    public boolean getAllSpecialty() {
        return setFlags(specialtyFlags, SPECIALTY_FLAGS_BITS, SPECIALTY_FLAGS_BITS_LEN);
    }

    public boolean setFlags(DataList<Bool> flags, long flagBits, int len) {
        for (int i = 0; i < len; i++) {
            if (!flags.get(i).value(((flagBits >>> i) & 1) == 1).save()) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
        super.finalize();
    }

    @Override
    public String toString() {
        return "GameData{" +
                "offset=" + offset +
                ", length=" + length +
                ", version=" + version +
                ", versionStart=" + versionStart +
                ", supportID=" + supportID +
                ", clover=" + clover +
                ", ticket=" + ticket +
                ", lastDateTime=" + lastDateTime +
                ", tutorialStep=" + tutorialStep +
                ", frogName='" + frogName + '\'' +
                ", frogAchieveId=" + frogAchieveId +
                ", frogMotion=" + frogMotion +
                ", home=" + home +
                ", drift=" + drift +
                ", restTime=" + restTime +
                ", lastTravelTime=" + lastTravelTime +
                ", standby=" + standby +
                ", standbyWait=" + standbyWait +
                ", bgmVolume=" + bgmVolume +
                ", seVolume=" + seVolume +
                ", noticeFlag=" + noticeFlag +
                ", tmpRaffleResult=" + tmpRaffleResult +
                '}';
    }

    @Override
    public boolean save() {
        // not need implementation
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean write(RandomAccessFile r) {
        try {
            r.seek(0);
            r.writeInt(version);
            r.writeInt(supportID);
            if (!hoten.write(r)) {
                return false;
            }
            if (!clover.write(r)) {
                return false;
            }
            if (!ticket.write(r)) {
                return false;
            }
            if (!cloverList.write(r)) {
                return false;
            }
            if (!lastDateTime.write(r)) {
                return false;
            }
            if (!mailListNextId.write(r)) {
                return false;
            }
            if (!mailList.write(r)) {
                return false;
            }
            if (!itemList.write(r)) {
                return false;
            }
            if (!bagList.write(r)) {
                return false;
            }
            if (!deskList.write(r)) {
                return false;
            }
            if (!bagListVirtual.write(r)) {
                return false;
            }
            if (!deskListVirtual.write(r)) {
                return false;
            }
            if (!collectFlags.write(r)) {
                return false;
            }
            if (!collectFailedCnt.write(r)) {
                return false;
            }
            if (!specialtyFlags.write(r)) {
                return false;
            }
            if (!eventTimerList.write(r)) {
                return false;
            }
            if (!eventActiveList.write(r)) {
                return false;
            }
            if (!tutorialStep.write(r)) {
                return false;
            }
            if (!firstFlag.write(r)) {
                return false;
            }
            if (!frogName.write(r)) {
                return false;
            }
            if (!frogAchieveId.write(r)) {
                return false;
            }
            if (!achieveFlags.write(r)) {
                return false;
            }
            if (!frogMotion.write(r)) {
                return false;
            }
            if (!home.write(r)) {
                return false;
            }
            if (!drift.write(r)) {
                return false;
            }
            if (!restTime.write(r)) {
                return false;
            }
            if (!lastTravelTime.write(r)) {
                return false;
            }
            if (!standby.write(r)) {
                return false;
            }
            if (!standbyWait.write(r)) {
                return false;
            }
            if (!bgmVolume.write(r)) {
                return false;
            }
            if (!seVolume.write(r)) {
                return false;
            }
            if (!noticeFlag.write(r)) {
                return false;
            }
            if (!gameFlags.write(r)) {
                return false;
            }
            if (!tmpRaffleResult.write(r)) {
                return false;
            }
            r.writeInt(versionStart);
            if (version >= 10500) {
                r.writeInt(iapCallBackCnt);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean writeToFile(File file) {
        try {
            return write(new RandomAccessFile(file, "rwd"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public interface OnLoadedListener {
        void onLoaded(GameData data);
    }
}