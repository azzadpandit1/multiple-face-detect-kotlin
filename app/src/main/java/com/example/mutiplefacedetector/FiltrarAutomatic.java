//package com.example.mutiplefacedetector;
//
//import android.animation.Animator;
//import android.animation.ValueAnimator;
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.SharedPreferences;
//import android.content.pm.PackageInfo;
//import android.content.res.Configuration;
//import android.hardware.usb.UsbConfiguration;
//import android.hardware.usb.UsbDevice;
//import android.hardware.usb.UsbDeviceConnection;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.preference.PreferenceManager;
//import android.provider.Settings$Global;
//import android.util.AttributeSet;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.Display;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebView;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.Switch;
//import android.widget.Toast;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import com.google.ads.mediation.admob.AdMobAdapter;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.LoadAdError;
//import com.google.android.gms.ads.interstitial.InterstitialAd;
//import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.play.core.review.C5425a;
//import com.google.android.play.core.review.ReviewInfo;
//import com.hexamob.drivers.R;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import p041f.C3944f;
//import p181l4.C5960b;
//import p190o4.C6079e;
//import p197q5.C6260f;
//import p197q5.C6263g;
//import p204t5.C6363a;
//import p204t5.C6364b;
//import p206u5.C6416c;
//import p206u5.C6421f;
//import p208v5.C6431b;
//
//@SuppressLint({"NewApi"})
//public class FiltrarAutomatic extends C3944f implements C6431b.C6432a {
//
//    /* renamed from: O0 */
//    static Context f18188O0 = null;
//
//    /* renamed from: P0 */
//    static String f18189P0 = "drivers";
//
//    /* renamed from: Q0 */
//    public static boolean f18190Q0 = false;
//
//    /* renamed from: R0 */
//    static SharedPreferences f18191R0 = null;
//
//    /* renamed from: S0 */
//    static C6421f f18192S0 = null;
//
//    /* renamed from: T0 */
//    static int f18193T0 = 1;
//    /* access modifiers changed from: private */
//
//    /* renamed from: U0 */
//    public static InterstitialAd f18194U0;
//
//    /* renamed from: V0 */
//    static AdRequest f18195V0;
//
//    /* renamed from: W0 */
//    public static Activity f18196W0;
//
//    /* renamed from: A */
//    String f18197A = "https://play.google.com/store/apps/details?id=com.hexamob.hexamobrecoverylite&referrer=utm_campaign=Drivers&utm_source=Boton";
//
//    /* renamed from: A0 */
//    Boolean f18198A0;
//
//    /* renamed from: B */
//    final int f18199B = 1;
//
//    /* renamed from: B0 */
//    C6260f f18200B0;
//
//    /* renamed from: C */
//    boolean f18201C = false;
//
//    /* renamed from: C0 */
//    LinearLayout f18202C0;
//
//    /* renamed from: D */
//    boolean f18203D = false;
//
//    /* renamed from: D0 */
//    FloatingActionButton f18204D0;
//
//    /* renamed from: E */
//    Toolbar f18205E;
//
//    /* renamed from: E0 */
//    private AdView f18206E0;
//
//    /* renamed from: F */
//    LinearLayout f18207F;
//
//    /* renamed from: F0 */
//    private boolean f18208F0;
//
//    /* renamed from: G */
//    LinearLayout f18209G;
//
//    /* renamed from: G0 */
//    C6421f f18210G0;
//
//    /* renamed from: H */
//    LinearLayout f18211H;
//
//    /* renamed from: H0 */
//    int f18212H0;
//
//    /* renamed from: I */
//    LinearLayout f18213I;
//
//    /* renamed from: I0 */
//    ViewGroup.LayoutParams f18214I0;
//
//    /* renamed from: J */
//    LinearLayout f18215J;
//
//    /* renamed from: J0 */
//    AdSize f18216J0;
//
//    /* renamed from: K */
//    LinearLayout f18217K;
//
//    /* renamed from: K0 */
//    public UsbConfiguration f18218K0;
//
//    /* renamed from: L */
//    LinearLayout f18219L;
//
//    /* renamed from: L0 */
//    public UsbDevice f18220L0;
//
//    /* renamed from: M */
//    LinearLayout f18221M;
//
//    /* renamed from: M0 */
//    public UsbDeviceConnection f18222M0;
//
//    /* renamed from: N */
//    LinearLayout f18223N;
//
//    /* renamed from: N0 */
//    private BroadcastReceiver f18224N0;
//
//    /* renamed from: O */
//    LinearLayout f18225O;
//
//    /* renamed from: P */
//    LinearLayout f18226P;
//
//    /* renamed from: Q */
//    LinearLayout f18227Q;
//
//    /* renamed from: R */
//    LinearLayout f18228R;
//
//    /* renamed from: S */
//    LinearLayout f18229S;
//
//    /* renamed from: T */
//    LinearLayout f18230T;
//
//    /* renamed from: U */
//    LinearLayout f18231U;
//
//    /* renamed from: V */
//    LinearLayout f18232V;
//
//    /* renamed from: W */
//    LinearLayout f18233W;
//
//    /* renamed from: X */
//    LinearLayout f18234X;
//
//    /* renamed from: Y */
//    LinearLayout f18235Y;
//
//    /* renamed from: Z */
//    LinearLayout f18236Z;
//
//    /* renamed from: a0 */
//    LinearLayout f18237a0;
//
//    /* renamed from: b0 */
//    LinearLayout f18238b0;
//
//    /* renamed from: c0 */
//    LinearLayout f18239c0;
//    /* access modifiers changed from: private */
//
//    /* renamed from: d0 */
//    public ProgressBar f18240d0;
//
//    /* renamed from: e0 */
//    SharedPreferences f18241e0 = null;
//
//    /* renamed from: f0 */
//    boolean f18242f0 = false;
//
//    /* renamed from: g0 */
//    String f18243g0 = "";
//
//    /* renamed from: h0 */
//    String f18244h0 = "";
//
//    /* renamed from: i0 */
//    String f18245i0 = "";
//
//    /* renamed from: j0 */
//    String f18246j0 = null;
//
//    /* renamed from: k0 */
//    private WebView f18247k0;
//
//    /* renamed from: l0 */
//    String f18248l0 = null;
//
//    /* renamed from: m0 */
//    Button f18249m0 = null;
//
//    /* renamed from: n0 */
//    FloatingActionButton f18250n0;
//
//    /* renamed from: o0 */
//    FloatingActionButton f18251o0;
//
//    /* renamed from: p0 */
//    LinearLayout f18252p0 = null;
//
//    /* renamed from: q0 */
//    LinearLayout f18253q0 = null;
//
//    /* renamed from: r0 */
//    PackageInfo f18254r0 = null;
//
//    /* renamed from: s0 */
//    String f18255s0;
//
//    /* renamed from: t0 */
//    String f18256t0 = "https://hexamob.com/filtrar-fabricante-root.php";
//
//    /* renamed from: u0 */
//    String f18257u0;
//
//    /* renamed from: v */
//    String f18258v = "https://play.google.com/store/apps/details?id=com.hexamob.howtoroot&referrer=utm_campaign=Drivers&utm_source=Boton";
//
//    /* renamed from: v0 */
//    ArrayList<C6263g> f18259v0;
//
//    /* renamed from: w */
//    String f18260w = "https://play.google.com/store/apps/details?id=com.hexamob.allandroidupdates&referrer=utm_campaign=Drivers&utm_source=Boton";
//
//    /* renamed from: w0 */
//    ArrayList<C6263g> f18261w0;
//
//    /* renamed from: x */
//    String f18262x = "https://play.google.com/store/apps/details?id=com.hexamob.rankgeawishbestbuy&referrer=utm_campaign=Drivers&utm_source=Boton";
//
//    /* renamed from: x0 */
//    RecyclerView f18263x0;
//
//    /* renamed from: y */
//    String f18264y = "https://play.google.com/store/apps/details?id=com.hexamob.puzzles&referrer=utm_campaign=Drivers&utm_source=Boton";
//
//    /* renamed from: y0 */
//    Boolean f18265y0;
//
//    /* renamed from: z */
//    String f18266z = "https://play.google.com/store/apps/details?id=com.Hexamob.GravityLapse";
//
//    /* renamed from: z0 */
//    C6416c f18267z0;
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$a */
//    class C5504a implements View.OnClickListener {
//        C5504a() {
//        }
//
//        public void onClick(View view) {
//            if (FiltrarAutomatic.this.f18223N.getVisibility() == 8) {
//                FiltrarAutomatic filtrarAutomatic = FiltrarAutomatic.this;
//                filtrarAutomatic.m16715d0(filtrarAutomatic.f18223N);
//                return;
//            }
//            FiltrarAutomatic filtrarAutomatic2 = FiltrarAutomatic.this;
//            filtrarAutomatic2.m16713b0(filtrarAutomatic2.f18223N);
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$b */
//    class C5505b implements View.OnClickListener {
//        C5505b() {
//        }
//
//        public void onClick(View view) {
//            if (FiltrarAutomatic.this.f18225O.getVisibility() == 8) {
//                FiltrarAutomatic filtrarAutomatic = FiltrarAutomatic.this;
//                filtrarAutomatic.m16715d0(filtrarAutomatic.f18225O);
//                return;
//            }
//            FiltrarAutomatic filtrarAutomatic2 = FiltrarAutomatic.this;
//            filtrarAutomatic2.m16713b0(filtrarAutomatic2.f18225O);
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$c */
//    class C5506c implements View.OnClickListener {
//        C5506c() {
//        }
//
//        public void onClick(View view) {
//            if (FiltrarAutomatic.this.f18226P.getVisibility() == 8) {
//                FiltrarAutomatic filtrarAutomatic = FiltrarAutomatic.this;
//                filtrarAutomatic.m16715d0(filtrarAutomatic.f18226P);
//                return;
//            }
//            FiltrarAutomatic filtrarAutomatic2 = FiltrarAutomatic.this;
//            filtrarAutomatic2.m16713b0(filtrarAutomatic2.f18226P);
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$d */
//    class C5507d implements View.OnClickListener {
//        C5507d() {
//        }
//
//        public void onClick(View view) {
//            if (FiltrarAutomatic.this.f18227Q.getVisibility() == 8) {
//                FiltrarAutomatic filtrarAutomatic = FiltrarAutomatic.this;
//                filtrarAutomatic.m16715d0(filtrarAutomatic.f18227Q);
//                return;
//            }
//            FiltrarAutomatic filtrarAutomatic2 = FiltrarAutomatic.this;
//            filtrarAutomatic2.m16713b0(filtrarAutomatic2.f18227Q);
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$e */
//    class C5508e implements View.OnClickListener {
//        C5508e() {
//        }
//
//        public void onClick(View view) {
//            PreferenceManager.getDefaultSharedPreferences(FiltrarAutomatic.this).edit().putBoolean("tutorialfabricantes", true).apply();
//            FiltrarAutomatic.this.startActivity(new Intent(FiltrarAutomatic.f18188O0, Filtrarfabricantes.class));
//            Log.d(FiltrarAutomatic.f18189P0, "Filtrar Fabricantes ");
//            FiltrarAutomatic.m16714c0();
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$f */
//    class C5509f implements View.OnClickListener {
//        C5509f() {
//        }
//
//        public void onClick(View view) {
//            if (!FiltrarAutomatic.this.f18198A0.booleanValue()) {
//                FiltrarAutomatic.this.m16721k0();
//            } else {
//                FiltrarAutomatic.this.m16712a0();
//            }
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$g */
//    class C5510g implements View.OnClickListener {
//        C5510g() {
//        }
//
//        public void onClick(View view) {
//            FiltrarAutomatic.this.mo24049j0();
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$h */
//    class C5511h implements Runnable {
//
//        /* renamed from: e */
//        final /* synthetic */ Handler f18275e;
//
//        /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$h$a */
//        class C5512a implements Runnable {
//            C5512a() {
//            }
//
//            public void run() {
//                FiltrarAutomatic.this.mo24047Q();
//                FiltrarAutomatic.this.f18240d0.setVisibility(8);
//                FiltrarAutomatic.this.getWindow().clearFlags(16);
//            }
//        }
//
//        C5511h(Handler handler) {
//            this.f18275e = handler;
//        }
//
//        public void run() {
//            try {
//                URL url = new URL(FiltrarAutomatic.this.f18256t0);
//                String str = FiltrarAutomatic.f18189P0;
//                Log.d(str, "FILTRARAUTOMATIC url= " + url + " --- " + FiltrarAutomatic.this.f18256t0);
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                String str2 = FiltrarAutomatic.f18189P0;
//                Log.d(str2, "FILTRARAUTOMATIC conn= " + httpURLConnection);
//                int responseCode = httpURLConnection.getResponseCode();
//                String str3 = FiltrarAutomatic.f18189P0;
//                Log.d(str3, "FILTRARAUTOMATIC responseCode= " + responseCode);
//                if (responseCode == 200) {
//                    FiltrarAutomatic filtrarAutomatic = FiltrarAutomatic.this;
//                    filtrarAutomatic.f18255s0 = filtrarAutomatic.m16717f0(httpURLConnection.getInputStream()).toString();
//                    String str4 = FiltrarAutomatic.f18189P0;
//                    Log.d(str4, "FILTRARAUTOMATIC jsonResult= " + FiltrarAutomatic.this.f18255s0);
//                } else {
//                    String str5 = FiltrarAutomatic.f18189P0;
//                    Log.d(str5, "FILTRARAUTOMATIC responseCode= " + responseCode);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            this.f18275e.post(new C5512a());
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$i */
//    class C5513i extends InterstitialAdLoadCallback {
//        C5513i() {
//        }
//
//        /* renamed from: a */
//        public void onAdLoaded(InterstitialAd interstitialAd) {
//            InterstitialAd unused = FiltrarAutomatic.f18194U0 = interstitialAd;
//            Log.i(FiltrarAutomatic.f18189P0, "onAdLoaded");
//        }
//
//        public void onAdFailedToLoad(LoadAdError loadAdError) {
//            Log.d(FiltrarAutomatic.f18189P0, loadAdError.toString());
//            InterstitialAd unused = FiltrarAutomatic.f18194U0 = null;
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$j */
//    class C5514j extends BroadcastReceiver {
//        C5514j() {
//        }
//
//        public void onReceive(Context context, Intent intent) {
//            boolean z;
//            boolean z2;
//            Intent intent2 = intent;
//            FiltrarAutomatic.this.f18265y0 = Boolean.valueOf(intent2.getBooleanExtra("connected", false));
//            Boolean valueOf = Boolean.valueOf(intent2.getBooleanExtra("mtp", false));
//            Boolean valueOf2 = Boolean.valueOf(intent2.getBooleanExtra("host_connected", false));
//            Boolean valueOf3 = Boolean.valueOf(intent2.getBooleanExtra("adb", false));
//            Boolean valueOf4 = Boolean.valueOf(intent2.getBooleanExtra("rndis", false));
//            Boolean valueOf5 = Boolean.valueOf(intent2.getBooleanExtra("USB_FUNCTION_PTP", false));
//            Boolean valueOf6 = Boolean.valueOf(intent2.getBooleanExtra("ptp", false));
//            Boolean valueOf7 = Boolean.valueOf(intent2.getBooleanExtra("audio_source", false));
//            Boolean valueOf8 = Boolean.valueOf(intent2.getBooleanExtra("midi", false));
//            Switch switchR = (Switch) FiltrarAutomatic.this.findViewById(R.id.switch_ON4);
//            switchR.setClickable(false);
//            Switch switchR2 = (Switch) FiltrarAutomatic.this.findViewById(R.id.switch_ONMTP);
//            switchR2.setClickable(false);
//            Switch switchR3 = (Switch) FiltrarAutomatic.this.findViewById(R.id.switch_host_OTG);
//            switchR3.setClickable(false);
//            Switch switchR4 = (Switch) FiltrarAutomatic.this.findViewById(R.id.switch_adbFunction);
//            switchR4.setClickable(false);
//            Switch switchR5 = (Switch) FiltrarAutomatic.this.findViewById(R.id.switch_rndisEtht);
//            switchR5.setClickable(false);
//            ((Switch) FiltrarAutomatic.this.findViewById(R.id.switch_PTPFunctio)).setClickable(false);
//            Switch switchR6 = (Switch) FiltrarAutomatic.this.findViewById(R.id.switch_PTP);
//            switchR6.setClickable(false);
//            Boolean bool = valueOf8;
//            Switch switchR7 = (Switch) FiltrarAutomatic.this.findViewById(R.id.switch_AudioSource);
//            switchR7.setClickable(false);
//            Switch switchR8 = switchR7;
//            Switch switchR9 = (Switch) FiltrarAutomatic.this.findViewById(R.id.switch_Midi);
//            switchR9.setClickable(false);
//            Switch switchR10 = switchR9;
//            if (FiltrarAutomatic.this.f18265y0.booleanValue()) {
//                switchR.setChecked(true);
//            } else {
//                switchR.setChecked(false);
//            }
//            if (!valueOf.booleanValue() || !FiltrarAutomatic.this.f18265y0.booleanValue()) {
//                z = false;
//                switchR2.setChecked(false);
//            } else {
//                switchR2.setChecked(true);
//                z = false;
//            }
//            if (valueOf2.booleanValue()) {
//                switchR3.setChecked(true);
//            } else {
//                switchR3.setChecked(z);
//            }
//            if (valueOf3.booleanValue()) {
//                switchR4.setChecked(true);
//            } else {
//                switchR4.setChecked(z);
//            }
//            if (valueOf4.booleanValue()) {
//                switchR5.setChecked(true);
//            } else {
//                switchR5.setChecked(z);
//            }
//            if (valueOf5.booleanValue()) {
//                switchR6.setChecked(true);
//            } else {
//                switchR6.setChecked(z);
//            }
//            if (!valueOf6.booleanValue() || !FiltrarAutomatic.this.f18265y0.booleanValue()) {
//                z2 = false;
//                switchR6.setChecked(false);
//            } else {
//                switchR6.setChecked(true);
//                z2 = false;
//            }
//            if (valueOf7.booleanValue()) {
//                switchR8.setChecked(true);
//            } else {
//                switchR8.setChecked(z2);
//            }
//            if (bool.booleanValue()) {
//                switchR10.setChecked(true);
//            } else {
//                switchR10.setChecked(z2);
//            }
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$k */
//    class C5515k implements Animator.AnimatorListener {
//
//        /* renamed from: a */
//        final /* synthetic */ LinearLayout f18280a;
//
//        C5515k(LinearLayout linearLayout) {
//            this.f18280a = linearLayout;
//        }
//
//        public void onAnimationCancel(Animator animator) {
//        }
//
//        public void onAnimationEnd(Animator animator) {
//            this.f18280a.setVisibility(8);
//        }
//
//        public void onAnimationRepeat(Animator animator) {
//        }
//
//        public void onAnimationStart(Animator animator) {
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$l */
//    class C5516l implements ValueAnimator.AnimatorUpdateListener {
//
//        /* renamed from: a */
//        final /* synthetic */ LinearLayout f18282a;
//
//        C5516l(LinearLayout linearLayout) {
//            this.f18282a = linearLayout;
//        }
//
//        public void onAnimationUpdate(ValueAnimator valueAnimator) {
//            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
//            ViewGroup.LayoutParams layoutParams = this.f18282a.getLayoutParams();
//            layoutParams.height = intValue;
//            this.f18282a.setLayoutParams(layoutParams);
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$m */
//    class C5517m implements View.OnClickListener {
//        C5517m() {
//        }
//
//        public void onClick(View view) {
//            if (FiltrarAutomatic.this.f18207F.getVisibility() == 8) {
//                FiltrarAutomatic filtrarAutomatic = FiltrarAutomatic.this;
//                filtrarAutomatic.m16715d0(filtrarAutomatic.f18207F);
//                return;
//            }
//            FiltrarAutomatic filtrarAutomatic2 = FiltrarAutomatic.this;
//            filtrarAutomatic2.m16713b0(filtrarAutomatic2.f18207F);
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$n */
//    class C5518n implements View.OnClickListener {
//        C5518n() {
//        }
//
//        public void onClick(View view) {
//            if (FiltrarAutomatic.this.f18209G.getVisibility() == 8) {
//                FiltrarAutomatic filtrarAutomatic = FiltrarAutomatic.this;
//                filtrarAutomatic.m16715d0(filtrarAutomatic.f18209G);
//                return;
//            }
//            FiltrarAutomatic filtrarAutomatic2 = FiltrarAutomatic.this;
//            filtrarAutomatic2.m16713b0(filtrarAutomatic2.f18209G);
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$o */
//    class C5519o implements View.OnClickListener {
//        C5519o() {
//        }
//
//        public void onClick(View view) {
//            if (FiltrarAutomatic.this.f18211H.getVisibility() == 8) {
//                FiltrarAutomatic filtrarAutomatic = FiltrarAutomatic.this;
//                filtrarAutomatic.m16715d0(filtrarAutomatic.f18211H);
//                return;
//            }
//            FiltrarAutomatic filtrarAutomatic2 = FiltrarAutomatic.this;
//            filtrarAutomatic2.m16713b0(filtrarAutomatic2.f18211H);
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$p */
//    class C5520p implements View.OnClickListener {
//        C5520p() {
//        }
//
//        public void onClick(View view) {
//            if (FiltrarAutomatic.this.f18213I.getVisibility() == 8) {
//                FiltrarAutomatic filtrarAutomatic = FiltrarAutomatic.this;
//                filtrarAutomatic.m16715d0(filtrarAutomatic.f18213I);
//                return;
//            }
//            FiltrarAutomatic filtrarAutomatic2 = FiltrarAutomatic.this;
//            filtrarAutomatic2.m16713b0(filtrarAutomatic2.f18213I);
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$q */
//    class C5521q implements View.OnClickListener {
//        C5521q() {
//        }
//
//        public void onClick(View view) {
//            if (FiltrarAutomatic.this.f18215J.getVisibility() == 8) {
//                FiltrarAutomatic filtrarAutomatic = FiltrarAutomatic.this;
//                filtrarAutomatic.m16715d0(filtrarAutomatic.f18215J);
//                return;
//            }
//            FiltrarAutomatic filtrarAutomatic2 = FiltrarAutomatic.this;
//            filtrarAutomatic2.m16713b0(filtrarAutomatic2.f18215J);
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$r */
//    class C5522r implements View.OnClickListener {
//        C5522r() {
//        }
//
//        public void onClick(View view) {
//            if (FiltrarAutomatic.this.f18217K.getVisibility() == 8) {
//                FiltrarAutomatic filtrarAutomatic = FiltrarAutomatic.this;
//                filtrarAutomatic.m16715d0(filtrarAutomatic.f18217K);
//                return;
//            }
//            FiltrarAutomatic filtrarAutomatic2 = FiltrarAutomatic.this;
//            filtrarAutomatic2.m16713b0(filtrarAutomatic2.f18217K);
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$s */
//    class C5523s implements View.OnClickListener {
//        C5523s() {
//        }
//
//        public void onClick(View view) {
//            if (FiltrarAutomatic.this.f18219L.getVisibility() == 8) {
//                FiltrarAutomatic filtrarAutomatic = FiltrarAutomatic.this;
//                filtrarAutomatic.m16715d0(filtrarAutomatic.f18219L);
//                return;
//            }
//            FiltrarAutomatic filtrarAutomatic2 = FiltrarAutomatic.this;
//            filtrarAutomatic2.f18219L = (LinearLayout) filtrarAutomatic2.findViewById(R.id.expandable1header7);
//            FiltrarAutomatic filtrarAutomatic3 = FiltrarAutomatic.this;
//            filtrarAutomatic3.m16713b0(filtrarAutomatic3.f18219L);
//        }
//    }
//
//    /* renamed from: com.hexamob.drivers.principalesclases.FiltrarAutomatic$t */
//    class C5524t implements View.OnClickListener {
//        C5524t() {
//        }
//
//        public void onClick(View view) {
//            if (FiltrarAutomatic.this.f18221M.getVisibility() == 8) {
//                FiltrarAutomatic filtrarAutomatic = FiltrarAutomatic.this;
//                filtrarAutomatic.m16715d0(filtrarAutomatic.f18221M);
//                return;
//            }
//            FiltrarAutomatic filtrarAutomatic2 = FiltrarAutomatic.this;
//            filtrarAutomatic2.m16713b0(filtrarAutomatic2.f18221M);
//        }
//    }
//
//    public FiltrarAutomatic() {
//        Boolean bool = Boolean.FALSE;
//        this.f18265y0 = bool;
//        this.f18267z0 = null;
//        this.f18198A0 = bool;
//        this.f18208F0 = false;
//        this.f18212H0 = 0;
//        this.f18218K0 = null;
//        this.f18220L0 = null;
//        this.f18222M0 = null;
//        this.f18224N0 = new C5514j();
//    }
//
//    /* renamed from: R */
//    private static void m16703R(String str, int i) {
//        Log.d(f18189P0, "-PUBLICIDAD SavePreferencesInt()-");
//        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(f18188O0).edit();
//        edit.putInt(str, i);
//        edit.apply();
//    }
//
//    /* renamed from: Z */
//    private Boolean m16711Z() {
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE");
//        registerReceiver(new C6431b(), intentFilter);
//        C6431b.f20453a = this;
//        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
//        return Boolean.valueOf(activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting());
//    }
//
//    /* access modifiers changed from: private */
//    /* renamed from: a0 */
//    public void m16712a0() {
//        this.f18198A0 = Boolean.FALSE;
//        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.botorateus);
//        this.f18250n0 = floatingActionButton;
//        floatingActionButton.animate().translationY(0.0f);
//        this.f18204D0.animate().translationY(0.0f);
//        this.f18204D0.setVisibility(8);
//        this.f18250n0.setVisibility(8);
//    }
//
//    /* access modifiers changed from: private */
//    /* renamed from: b0 */
//    public void m16713b0(LinearLayout linearLayout) {
//        ValueAnimator l0 = m16722l0(linearLayout.getHeight(), 0, linearLayout);
//        l0.addListener(new C5515k(linearLayout));
//        l0.start();
//    }
//
//    /* renamed from: c0 */
//    public static void m16714c0() {
//        String str = f18189P0;
//        Log.d(str, "-PUBLICIDAD DISPLAYINTERSTITIAL()- mInterstitialAd<-------------->" + f18194U0);
//        f18192S0 = new C6421f(f18188O0);
//        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(f18188O0);
//        f18191R0 = defaultSharedPreferences;
//        f18193T0 = defaultSharedPreferences.getInt("icontadorinter", f18193T0);
//        if (f18192S0.mo25311b() == 0) {
//            String str2 = f18189P0;
//            Log.d(str2, "PUBLICIDAD DISPLAYINTERSTITIAL() icontadorinter--------------------------- =" + f18193T0);
//            int i = f18193T0;
//            if (i % 6 == 0 && i != 0) {
//                String str3 = f18189P0;
//                Log.d(str3, "PUBLICIDAD DISPLAYINTERSTITIAL() DISPLAY INTERSTITIAL DINS IF %6 mInterstitialAd< *********************" + f18194U0);
//                String str4 = f18189P0;
//                Log.d(str4, "PUBLICIDAD DISPLAYINTERSTITIAL() DISPLAY INTERSTITIAL DINS IF %6 adRequestinterstitial< *********************" + f18195V0);
//                InterstitialAd interstitialAd = f18194U0;
//                if (interstitialAd != null) {
//                    interstitialAd.show(f18196W0);
//                } else {
//                    Log.d("TAG", "PUBLICIDAD DISPLAYINTERSTITIAL() The interstitial ad wasn't ready yet.");
//                }
//            }
//        }
//        int i2 = f18193T0 + 1;
//        f18193T0 = i2;
//        m16703R("icontadorinter", i2);
//    }
//
//    /* access modifiers changed from: private */
//    /* renamed from: d0 */
//    public void m16715d0(LinearLayout linearLayout) {
//        linearLayout.setVisibility(0);
//        linearLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
//        m16722l0(0, linearLayout.getMeasuredHeight(), linearLayout).start();
//    }
//
//    /* renamed from: e0 */
//    private AdSize m16716e0() {
//        Log.d(f18189P0, "-PUBLICIDAD GETADSIZE()-");
//        Display defaultDisplay = getWindowManager().getDefaultDisplay();
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        defaultDisplay.getMetrics(displayMetrics);
//        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(f18188O0, (int) (((float) displayMetrics.widthPixels) / displayMetrics.density));
//    }
//
//    /* access modifiers changed from: private */
//    /* renamed from: f0 */
//    public StringBuilder m16717f0(InputStream inputStream) {
//        StringBuilder sb = new StringBuilder();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        while (true) {
//            try {
//                String readLine = bufferedReader.readLine();
//                if (readLine == null) {
//                    break;
//                }
//                sb.append(readLine);
//            } catch (IOException unused) {
//                Toast.makeText(getApplication().getApplicationContext(), "Not available", 1).show();
//            }
//        }
//        return sb;
//    }
//
//    /* access modifiers changed from: private */
//    /* renamed from: g0 */
//    public static /* synthetic */ void m16718g0(C6079e eVar) {
//        Log.d(f18189P0, "In-app review returned.");
//        Toast.makeText(f18188O0, "In-app review returned.", 0).show();
//    }
//
//    /* access modifiers changed from: private */
//    /* renamed from: h0 */
//    public /* synthetic */ void m16719h0(C5960b bVar, C6079e eVar) {
//        try {
//            if (eVar.mo24876g()) {
//                bVar.mo23908a(this, (ReviewInfo) eVar.mo24874e()).mo24870a(new C6364b());
//            }
//        } catch (Exception e) {
//            Toast.makeText(f18188O0, "Exception from openReview():", 0).show();
//            String str = f18189P0;
//            Log.d(str, "Exception from openReview():" + e);
//        }
//    }
//
//    /* renamed from: i0 */
//    private void m16720i0() {
//        AdRequest.Builder builder;
//        if (this.f18210G0.mo25310a() == 1) {
//            Log.d(f18189P0, "--PUBLICIDAD -- loadBannerPUBLICIDAD prefConsent.getConsent() == 1");
//            builder = new AdRequest.Builder();
//        } else {
//            Log.d(f18189P0, "--PUBLICIDAD -- loadBannerPUBLICIDAD prefConsent.getConsent() != 1");
//            Bundle bundle = new Bundle();
//            bundle.putString("npa", "1");
//            builder = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, bundle);
//        }
//        this.f18206E0.loadAd(builder.build());
//    }
//
//    /* access modifiers changed from: private */
//    /* renamed from: k0 */
//    public void m16721k0() {
//        this.f18198A0 = Boolean.TRUE;
//        this.f18250n0.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
//        this.f18204D0.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
//        this.f18204D0.setVisibility(0);
//        this.f18250n0.setVisibility(0);
//    }
//
//    /* renamed from: l0 */
//    private ValueAnimator m16722l0(int i, int i2, LinearLayout linearLayout) {
//        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, i2});
//        ofInt.addUpdateListener(new C5516l(linearLayout));
//        return ofInt;
//    }
//
//    /* JADX WARNING: Code restructure failed: missing block: B:12:0x00c7, code lost:
//        if (r5.contains("unknown") != false) goto L_0x00c9;
//     */
//    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
//        android.widget.Toast.makeText(getApplication().getApplicationContext(), "Not available", 1).show();
//     */
//    /* JADX WARNING: Code restructure failed: missing block: B:46:0x038a, code lost:
//        android.widget.Toast.makeText(getApplication().getApplicationContext(), "Connection failed", 1).show();
//     */
//    /* JADX WARNING: Failed to process nested try/catch */
//    /* JADX WARNING: Missing exception handler attribute for start block: B:44:0x0378 */
//    /* renamed from: Q */
//    /* Code decompiled incorrectly, please refer to instructions dump. */
//    public void mo24047Q() {
//        /*
//            r15 = this;
//            java.lang.String r0 = "UNKNOWN"
//            java.lang.String r1 = " FILTRARAUTOMATIC listadodispositivos "
//            r2 = 0
//            r3 = 1
//            java.lang.String r4 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r5.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r6 = "FILTRARAUTOMATIC jsonresponse size="
//            r5.append(r6)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r6 = r15.f18255s0     // Catch:{ JSONException -> 0x0378 }
//            r5.append(r6)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r5 = r5.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r4, r5)     // Catch:{ JSONException -> 0x0378 }
//            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r5 = r15.f18255s0     // Catch:{ JSONException -> 0x0378 }
//            r4.<init>(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r5 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r6.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r7 = " FILTRARAUTOMATIC listadodispositivos jsonArray "
//            r6.append(r7)     // Catch:{ JSONException -> 0x0378 }
//            r6.append(r4)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r6 = r6.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r5, r6)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r5 = android.os.Build.MANUFACTURER     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r6 = android.os.Build.BRAND     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r8.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r9 = " FILTRARAUTOMATIC fabricantelocal0= "
//            r8.append(r9)     // Catch:{ JSONException -> 0x0378 }
//            r8.append(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r8 = r8.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r8)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r8.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r9 = " FILTRARAUTOMATIC marcalocal0= "
//            r8.append(r9)     // Catch:{ JSONException -> 0x0378 }
//            r8.append(r6)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r8 = r8.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r8)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.Boolean r7 = java.lang.Boolean.FALSE     // Catch:{ JSONException -> 0x0378 }
//            r8 = 0
//        L_0x006e:
//            int r9 = r4.length()     // Catch:{ JSONException -> 0x0378 }
//            int r9 = r9 + r3
//            if (r8 >= r9) goto L_0x0345
//            if (r8 != 0) goto L_0x0079
//            goto L_0x0341
//        L_0x0079:
//            java.lang.String r9 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r10.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = " FILTRARAUTOMATIC fabricantelocal ELSE "
//            r10.append(r11)     // Catch:{ JSONException -> 0x0378 }
//            r10.append(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r10 = r10.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r9, r10)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r9 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r10.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = " FILTRARAUTOMATIC marcalocal  ELSE "
//            r10.append(r11)     // Catch:{ JSONException -> 0x0378 }
//            r10.append(r6)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r10 = r10.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r9, r10)     // Catch:{ JSONException -> 0x0378 }
//            int r9 = r8 + -1
//            org.json.JSONObject r10 = r4.getJSONObject(r9)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = "fabricante"
//            java.lang.String r10 = r10.getString(r11)     // Catch:{ JSONException -> 0x0378 }
//            org.json.JSONObject r9 = r4.getJSONObject(r9)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = "rutaimagenfabricante"
//            java.lang.String r9 = r9.getString(r11)     // Catch:{ JSONException -> 0x0378 }
//            boolean r11 = r5.contains(r0)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = "unknown"
//            if (r11 != 0) goto L_0x00c9
//            boolean r11 = r5.contains(r12)     // Catch:{ JSONException -> 0x0378 }
//            if (r11 == 0) goto L_0x014d
//        L_0x00c9:
//            java.lang.String r5 = android.os.Build.BRAND     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r13.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r14 = " FILTRARAUTOMATIC DENTRO IF fabricante0.5= "
//            r13.append(r14)     // Catch:{ JSONException -> 0x0378 }
//            r13.append(r10)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r13 = r13.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r11, r13)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r13.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r14 = " FILTRARAUTOMATIC DENTRO IF fabricantelocal0.5= "
//            r13.append(r14)     // Catch:{ JSONException -> 0x0378 }
//            r13.append(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r13 = r13.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r11, r13)     // Catch:{ JSONException -> 0x0378 }
//            boolean r11 = r10.equalsIgnoreCase(r5)     // Catch:{ JSONException -> 0x0378 }
//            if (r11 == 0) goto L_0x014d
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r13 = " DENTRO IF fabricante1= "
//            r11.append(r13)     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r10)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r11)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r13 = " DENTRO IF fabricantelocal1= "
//            r11.append(r13)     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r11)     // Catch:{ JSONException -> 0x0378 }
//            java.util.ArrayList<q5.g> r7 = r15.f18259v0     // Catch:{ JSONException -> 0x0378 }
//            q5.g r11 = new q5.g     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>(r10, r9)     // Catch:{ JSONException -> 0x0378 }
//            r7.add(r11)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r13 = " FILTRARAUTOMATIC listadodispositivos1 "
//            r11.append(r13)     // Catch:{ JSONException -> 0x0378 }
//            java.util.ArrayList<q5.g> r13 = r15.f18259v0     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r13)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r11)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.Boolean r7 = java.lang.Boolean.TRUE     // Catch:{ JSONException -> 0x0378 }
//        L_0x014d:
//            boolean r11 = r6.contains(r0)     // Catch:{ JSONException -> 0x0378 }
//            if (r11 != 0) goto L_0x0159
//            boolean r11 = r6.contains(r12)     // Catch:{ JSONException -> 0x0378 }
//            if (r11 == 0) goto L_0x01c5
//        L_0x0159:
//            java.lang.String r5 = android.os.Build.MANUFACTURER     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r12.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r13 = " FILTRARAUTOMATIC DENTRO IF fabricante1.5= "
//            r12.append(r13)     // Catch:{ JSONException -> 0x0378 }
//            r12.append(r10)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = r12.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r11, r12)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r12.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r13 = " FILTRARAUTOMATIC DENTRO IF fabricantelocal1.5= "
//            r12.append(r13)     // Catch:{ JSONException -> 0x0378 }
//            r12.append(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = r12.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r11, r12)     // Catch:{ JSONException -> 0x0378 }
//            boolean r11 = r10.equalsIgnoreCase(r5)     // Catch:{ JSONException -> 0x0378 }
//            if (r11 == 0) goto L_0x01c5
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = " FILTRARAUTOMATIC DENTRO IF fabricante2= "
//            r11.append(r12)     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r10)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r11)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = " FILTRARAUTOMATIC DENTRO IF fabricantelocal2= "
//            r11.append(r12)     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r11)     // Catch:{ JSONException -> 0x0378 }
//            java.util.ArrayList<q5.g> r7 = r15.f18259v0     // Catch:{ JSONException -> 0x0378 }
//            q5.g r11 = new q5.g     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>(r10, r9)     // Catch:{ JSONException -> 0x0378 }
//            r7.add(r11)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.Boolean r7 = java.lang.Boolean.TRUE     // Catch:{ JSONException -> 0x0378 }
//        L_0x01c5:
//            boolean r11 = r5.equalsIgnoreCase(r6)     // Catch:{ JSONException -> 0x0378 }
//            if (r11 == 0) goto L_0x0257
//            java.lang.String r5 = android.os.Build.MANUFACTURER     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r12.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r13 = " FILTRARAUTOMATIC DENTRO IF fabricante2.5= "
//            r12.append(r13)     // Catch:{ JSONException -> 0x0378 }
//            r12.append(r10)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = r12.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r11, r12)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r12.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r13 = " FILTRARAUTOMATIC DENTRO IF fabricantelocal2.5= "
//            r12.append(r13)     // Catch:{ JSONException -> 0x0378 }
//            r12.append(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = r12.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r11, r12)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = "LG"
//            boolean r11 = r5.equalsIgnoreCase(r11)     // Catch:{ JSONException -> 0x0378 }
//            if (r11 == 0) goto L_0x0257
//            java.lang.String r5 = "LGE"
//            boolean r11 = r10.equalsIgnoreCase(r5)     // Catch:{ JSONException -> 0x0378 }
//            if (r11 == 0) goto L_0x0257
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = " FILTRARAUTOMATIC DENTRO IF fabricante3= "
//            r11.append(r12)     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r10)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r11)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = " FILTRARAUTOMATIC DENTRO IF fabricantelocal3= "
//            r11.append(r12)     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r11)     // Catch:{ JSONException -> 0x0378 }
//            java.util.ArrayList<q5.g> r7 = r15.f18259v0     // Catch:{ JSONException -> 0x0378 }
//            q5.g r11 = new q5.g     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>(r10, r9)     // Catch:{ JSONException -> 0x0378 }
//            r7.add(r11)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>()     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r1)     // Catch:{ JSONException -> 0x0378 }
//            java.util.ArrayList<q5.g> r12 = r15.f18259v0     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r12)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r11)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.Boolean r7 = java.lang.Boolean.TRUE     // Catch:{ JSONException -> 0x0378 }
//        L_0x0257:
//            boolean r11 = r5.equalsIgnoreCase(r6)     // Catch:{ JSONException -> 0x0378 }
//            if (r11 != 0) goto L_0x02e7
//            java.lang.String r5 = android.os.Build.BRAND     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r12.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r13 = " FILTRARAUTOMATIC DENTRO IF fabricante3.5= "
//            r12.append(r13)     // Catch:{ JSONException -> 0x0378 }
//            r12.append(r10)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = r12.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r11, r12)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r12.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r13 = " FILTRARAUTOMATIC DENTRO IF fabricantelocal3.5= "
//            r12.append(r13)     // Catch:{ JSONException -> 0x0378 }
//            r12.append(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = r12.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r11, r12)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r10.toUpperCase()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = r5.toUpperCase()     // Catch:{ JSONException -> 0x0378 }
//            boolean r11 = r11.equals(r12)     // Catch:{ JSONException -> 0x0378 }
//            if (r11 == 0) goto L_0x02e7
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = " FILTRARAUTOMATIC DENTRO IF fabricante4= "
//            r11.append(r12)     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r10)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r11)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = " FILTRARAUTOMATIC  DENTRO IF fabricantelocal4= "
//            r11.append(r12)     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r11)     // Catch:{ JSONException -> 0x0378 }
//            java.util.ArrayList<q5.g> r7 = r15.f18259v0     // Catch:{ JSONException -> 0x0378 }
//            q5.g r11 = new q5.g     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>(r10, r9)     // Catch:{ JSONException -> 0x0378 }
//            r7.add(r11)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>()     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r1)     // Catch:{ JSONException -> 0x0378 }
//            java.util.ArrayList<q5.g> r12 = r15.f18259v0     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r12)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r11)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.Boolean r7 = java.lang.Boolean.TRUE     // Catch:{ JSONException -> 0x0378 }
//        L_0x02e7:
//            boolean r11 = r10.equalsIgnoreCase(r5)     // Catch:{ JSONException -> 0x0378 }
//            if (r11 == 0) goto L_0x0341
//            boolean r11 = r7.booleanValue()     // Catch:{ JSONException -> 0x0378 }
//            if (r11 != 0) goto L_0x0341
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = " FILTRARAUTOMATIC DENTRO IF fabricante5= "
//            r11.append(r12)     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r10)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r11)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r12 = " FILTRARAUTOMATIC DENTRO IF fabricantelocal5= "
//            r11.append(r12)     // Catch:{ JSONException -> 0x0378 }
//            r11.append(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r11)     // Catch:{ JSONException -> 0x0378 }
//            java.util.ArrayList<q5.g> r7 = r15.f18259v0     // Catch:{ JSONException -> 0x0378 }
//            q5.g r11 = new q5.g     // Catch:{ JSONException -> 0x0378 }
//            r11.<init>(r10, r9)     // Catch:{ JSONException -> 0x0378 }
//            r7.add(r11)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r7 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r9.<init>()     // Catch:{ JSONException -> 0x0378 }
//            r9.append(r1)     // Catch:{ JSONException -> 0x0378 }
//            java.util.ArrayList<q5.g> r10 = r15.f18259v0     // Catch:{ JSONException -> 0x0378 }
//            r9.append(r10)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r9 = r9.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r7, r9)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.Boolean r7 = java.lang.Boolean.FALSE     // Catch:{ JSONException -> 0x0378 }
//        L_0x0341:
//            int r8 = r8 + 1
//            goto L_0x006e
//        L_0x0345:
//            java.util.ArrayList<q5.g> r0 = r15.f18259v0     // Catch:{ JSONException -> 0x0378 }
//            boolean r0 = r0.isEmpty()     // Catch:{ JSONException -> 0x0378 }
//            if (r0 == 0) goto L_0x039b
//            java.lang.String r0 = f18189P0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0378 }
//            r4.<init>()     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r5 = " FILTRARAUTOMATIC IS EMPTY "
//            r4.append(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.util.ArrayList<q5.g> r5 = r15.f18259v0     // Catch:{ JSONException -> 0x0378 }
//            r4.append(r5)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r4 = r4.toString()     // Catch:{ JSONException -> 0x0378 }
//            android.util.Log.d(r0, r4)     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r0 = "Android"
//            r15.f18243g0 = r0     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r0 = "https://hexamob.com/wp-content/uploads/brandsimages/android-logo.png"
//            java.util.ArrayList<q5.g> r4 = r15.f18259v0     // Catch:{ JSONException -> 0x0378 }
//            q5.g r5 = new q5.g     // Catch:{ JSONException -> 0x0378 }
//            java.lang.String r6 = r15.f18243g0     // Catch:{ JSONException -> 0x0378 }
//            r5.<init>(r6, r0)     // Catch:{ JSONException -> 0x0378 }
//            r4.add(r5)     // Catch:{ JSONException -> 0x0378 }
//            goto L_0x039b
//        L_0x0378:
//            android.app.Application r0 = r15.getApplication()     // Catch:{ NullPointerException -> 0x038a }
//            android.content.Context r0 = r0.getApplicationContext()     // Catch:{ NullPointerException -> 0x038a }
//            java.lang.String r4 = "Not available"
//            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r4, r3)     // Catch:{ NullPointerException -> 0x038a }
//            r0.show()     // Catch:{ NullPointerException -> 0x038a }
//            goto L_0x039b
//        L_0x038a:
//            android.app.Application r0 = r15.getApplication()
//            android.content.Context r0 = r0.getApplicationContext()
//            java.lang.String r4 = "Connection failed"
//            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r4, r3)
//            r0.show()
//        L_0x039b:
//            f18190Q0 = r3
//            q5.f r0 = new q5.f
//            java.util.ArrayList<q5.g> r3 = r15.f18259v0
//            android.content.Context r4 = f18188O0
//            r0.<init>(r3, r4)
//            r15.f18200B0 = r0
//            java.lang.String r0 = f18189P0
//            java.lang.StringBuilder r3 = new java.lang.StringBuilder
//            r3.<init>()
//            r3.append(r1)
//            java.util.ArrayList<q5.g> r1 = r15.f18259v0
//            r3.append(r1)
//            java.lang.String r1 = r3.toString()
//            android.util.Log.d(r0, r1)
//            androidx.recyclerview.widget.RecyclerView r0 = r15.f18263x0
//            q5.f r1 = r15.f18200B0
//            r0.setAdapter(r1)
//            q5.f r0 = r15.f18200B0
//            r0.mo3968j()
//            androidx.recyclerview.widget.RecyclerView r0 = r15.f18263x0
//            r0.setVisibility(r2)
//            return
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.hexamob.drivers.principalesclases.FiltrarAutomatic.mo24047Q():void");
//    }
//
//    /* renamed from: j */
//    public void mo24048j(boolean z) {
//    }
//
//    /* renamed from: j0 */
//    public void mo24049j0() {
//        C5960b a = C5425a.m16538a(this);
//        a.mo23909b().mo24870a(new C6363a(this, a));
//    }
//
//    /* renamed from: m0 */
//    public void mo24050m0() {
//        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
//        Handler handler = new Handler(Looper.getMainLooper());
//        this.f18240d0 = new ProgressBar(this, (AttributeSet) null, 16842874);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
//        layoutParams.addRule(13);
//        ((RelativeLayout) findViewById(R.id.RLContenedorGeneral)).addView(this.f18240d0, layoutParams);
//        this.f18240d0.setVisibility(0);
//        getWindow().setFlags(16, 16);
//        newSingleThreadExecutor.execute(new C5511h(handler));
//    }
//
//    public void onConfigurationChanged(Configuration configuration) {
//        super.onConfigurationChanged(configuration);
//    }
//
//    /* access modifiers changed from: protected */
//    @SuppressLint({"SetJavaScriptEnabled"})
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        setContentView((int) R.layout.fabricantes_automatico);
//        if (getResources().getBoolean(R.bool.portrait_only)) {
//            setRequestedOrientation(1);
//        }
//        Log.d(f18189P0, "--FILTRAR AUTOMATIC ON CREATE--");
//        f18188O0 = this;
//        if (!m16711Z().booleanValue()) {
//            Toast.makeText(getApplication().getApplicationContext(), "NOT CONECTION", 1).show();
//        }
//        f18196W0 = this;
//        this.f18241e0 = PreferenceManager.getDefaultSharedPreferences(f18188O0);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
//        this.f18205E = toolbar;
//        if (toolbar != null) {
//            mo20601L(toolbar);
//        }
//        f18192S0 = new C6421f(f18188O0);
//        this.f18204D0 = (FloatingActionButton) findViewById(R.id.botonoads);
//        C6416c cVar = new C6416c(this.f18204D0, f18188O0, this);
//        this.f18267z0 = cVar;
//        cVar.mo25304c();
//        f18191R0 = PreferenceManager.getDefaultSharedPreferences(f18188O0);
//        f18192S0 = new C6421f(f18188O0);
//        this.f18210G0 = new C6421f(f18188O0);
//        this.f18202C0 = (LinearLayout) findViewById(R.id.BannerAdmob);
//        AdView adView = new AdView(this);
//        this.f18206E0 = adView;
//        adView.setAdUnitId("ca-app-pub-0217939415560711/1475954366");
//        AdSize e0 = m16716e0();
//        this.f18216J0 = e0;
//        this.f18206E0.setAdSize(e0);
//        this.f18212H0 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("iAdSizeHeight", this.f18212H0);
//        ViewGroup.LayoutParams layoutParams = this.f18202C0.getLayoutParams();
//        this.f18214I0 = layoutParams;
//        layoutParams.height = this.f18212H0;
//        this.f18202C0.setLayoutParams(layoutParams);
//        this.f18202C0.addView(this.f18206E0);
//        m16720i0();
//        if (this.f18210G0.mo25310a() == 1) {
//            f18195V0 = new AdRequest.Builder().build();
//            Log.d(f18189P0, "--PUBLICIDAD -- prefConsent.getConsent() != 1");
//        } else {
//            Bundle bundle2 = new Bundle();
//            bundle2.putString("npa", "1");
//            f18195V0 = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, bundle2).build();
//        }
//        InterstitialAd.load(this, "ca-app-pub-0217939415560711/3755096361", f18195V0, new C5513i());
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewfabricantes);
//        this.f18263x0 = recyclerView;
//        recyclerView.setLayoutManager(new LinearLayoutManager(f18188O0));
//        Switch switchR = (Switch) findViewById(R.id.switch_ON1);
//        switchR.setClickable(false);
//        if (Settings$Global.getInt(f18188O0.getContentResolver(), "development_settings_enabled", 0) == 0) {
//            switchR.setChecked(false);
//        } else {
//            switchR.setChecked(true);
//        }
//        Switch switchR2 = (Switch) findViewById(R.id.switch_ON2);
//        switchR2.setClickable(false);
//        if (Settings$Global.getInt(f18188O0.getContentResolver(), "usb_mass_storage_enabled", 0) == 0) {
//            switchR2.setChecked(false);
//        } else {
//            switchR2.setChecked(true);
//        }
//        Switch switchR3 = (Switch) findViewById(R.id.switch_ON3);
//        switchR3.setClickable(false);
//        if (Settings$Global.getInt(f18188O0.getContentResolver(), "adb_enabled", 0) == 0) {
//            switchR3.setChecked(false);
//        } else {
//            switchR3.setChecked(true);
//        }
//        this.f18207F = (LinearLayout) findViewById(R.id.expandable1);
//        this.f18209G = (LinearLayout) findViewById(R.id.expandable2);
//        this.f18211H = (LinearLayout) findViewById(R.id.expandable3);
//        this.f18213I = (LinearLayout) findViewById(R.id.expandable4);
//        this.f18215J = (LinearLayout) findViewById(R.id.expandable5);
//        this.f18217K = (LinearLayout) findViewById(R.id.expandable6);
//        this.f18219L = (LinearLayout) findViewById(R.id.expandable7);
//        this.f18221M = (LinearLayout) findViewById(R.id.expandable8);
//        this.f18223N = (LinearLayout) findViewById(R.id.expandable9);
//        this.f18225O = (LinearLayout) findViewById(R.id.expandable10);
//        this.f18226P = (LinearLayout) findViewById(R.id.expandable11);
//        this.f18227Q = (LinearLayout) findViewById(R.id.expandable12);
//        this.f18207F.setVisibility(8);
//        this.f18209G.setVisibility(8);
//        this.f18211H.setVisibility(8);
//        this.f18213I.setVisibility(8);
//        this.f18215J.setVisibility(8);
//        this.f18217K.setVisibility(8);
//        this.f18219L.setVisibility(8);
//        this.f18221M.setVisibility(8);
//        this.f18223N.setVisibility(8);
//        this.f18225O.setVisibility(8);
//        this.f18226P.setVisibility(8);
//        this.f18227Q.setVisibility(8);
//        this.f18228R = (LinearLayout) findViewById(R.id.expandable1header1);
//        this.f18229S = (LinearLayout) findViewById(R.id.expandable1header2);
//        this.f18230T = (LinearLayout) findViewById(R.id.expandable1header3);
//        this.f18231U = (LinearLayout) findViewById(R.id.expandable1header4);
//        this.f18232V = (LinearLayout) findViewById(R.id.expandable1header5);
//        this.f18233W = (LinearLayout) findViewById(R.id.expandable1header6);
//        this.f18234X = (LinearLayout) findViewById(R.id.expandable1header7);
//        this.f18235Y = (LinearLayout) findViewById(R.id.expandable1header8);
//        this.f18236Z = (LinearLayout) findViewById(R.id.expandable1header9);
//        this.f18237a0 = (LinearLayout) findViewById(R.id.expandable1header10);
//        this.f18238b0 = (LinearLayout) findViewById(R.id.expandable1header11);
//        this.f18239c0 = (LinearLayout) findViewById(R.id.expandable1header12);
//        this.f18228R.setOnClickListener(new C5517m());
//        this.f18229S.setOnClickListener(new C5518n());
//        this.f18230T.setOnClickListener(new C5519o());
//        this.f18231U.setOnClickListener(new C5520p());
//        this.f18232V.setOnClickListener(new C5521q());
//        this.f18233W.setOnClickListener(new C5522r());
//        this.f18234X.setOnClickListener(new C5523s());
//        this.f18235Y.setOnClickListener(new C5524t());
//        this.f18236Z.setOnClickListener(new C5504a());
//        this.f18237a0.setOnClickListener(new C5505b());
//        this.f18238b0.setOnClickListener(new C5506c());
//        this.f18239c0.setOnClickListener(new C5507d());
//        ((Button) findViewById(R.id.buttonmanual)).setOnClickListener(new C5508e());
//        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButtonAdd);
//        this.f18251o0 = floatingActionButton;
//        floatingActionButton.setOnClickListener(new C5509f());
//        FloatingActionButton floatingActionButton2 = (FloatingActionButton) findViewById(R.id.botorateus);
//        this.f18250n0 = floatingActionButton2;
//        floatingActionButton2.setOnClickListener(new C5510g());
//        this.f18259v0 = new ArrayList<>();
//        this.f18261w0 = new ArrayList<>();
//        mo24050m0();
//        if (getIntent().getBooleanExtra("Exit me", false)) {
//            finish();
//        }
//    }
//
//    /* access modifiers changed from: protected */
//    public void onDestroy() {
//        AdView adView = this.f18206E0;
//        if (adView != null) {
//            adView.destroy();
//        }
//        super.onDestroy();
//    }
//
//    public boolean onOptionsItemSelected(MenuItem menuItem) {
//        int itemId = menuItem.getItemId();
//        if (itemId == R.id.button_compartir) {
//            Intent intent = new Intent("android.intent.action.SEND");
//            intent.setType("text/plain");
//            String url = this.f18247k0.getUrl();
//            this.f18257u0 = url;
//            intent.putExtra("android.intent.extra.TEXT", url.replace("m.hexamob", "hexamob"));
//            startActivity(Intent.createChooser(intent, "Share via"));
//            return true;
//        } else if (itemId != R.id.button_refresh) {
//            return super.onOptionsItemSelected(menuItem);
//        } else {
//            this.f18247k0.reload();
//            return true;
//        }
//    }
//
//    /* access modifiers changed from: protected */
//    public void onPause() {
//        Log.d(f18189P0, "FILTRAR AUTOMATIC ONPAUSE-");
//        try {
//            unregisterReceiver(this.f18224N0);
//        } catch (IllegalArgumentException e) {
//            if (!e.getMessage().contains("Receiver not registered")) {
//                throw e;
//            }
//        }
//        AdView adView = this.f18206E0;
//        if (adView != null) {
//            adView.pause();
//        }
//        super.onPause();
//    }
//
//    /* access modifiers changed from: protected */
//    public void onResume() {
//        super.onResume();
//        Log.d(f18189P0, "FILTRAR AUTOMATIC ONRESUME-");
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("android.hardware.usb.action.USB_STATE");
//        registerReceiver(this.f18224N0, intentFilter);
//        if (f18192S0.mo25311b() == 0) {
//            String str = f18189P0;
//            Log.d(str, "prefsInapp.getRemoveAd()=0  ONRESUME  ADB 0 Btton" + this.f18204D0);
//            AdView adView = this.f18206E0;
//            if (adView != null) {
//                adView.resume();
//                return;
//            }
//            return;
//        }
//        this.f18206E0.setVisibility(8);
//        f18194U0 = null;
//        this.f18204D0.setVisibility(8);
//        this.f18202C0.setVisibility(8);
//    }
//
//    public void onStart() {
//        super.onStart();
//        Log.d(f18189P0, "FILTRAR AUTOMATIC ONSTART-");
//    }
//
//    /* access modifiers changed from: protected */
//    public void onStop() {
//        super.onStop();
//        Log.d(f18189P0, "FILTRAR AUTOMATIC ONSTOP-");
//        try {
//            unregisterReceiver(this.f18224N0);
//        } catch (IllegalArgumentException e) {
//            if (!e.getMessage().contains("Receiver not registered")) {
//                throw e;
//            }
//        }
//    }
//}