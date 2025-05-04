# PulsingLoadingButton 🚀

[![](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/Jaunger/PulsingLoadingButton)
[![](https://img.shields.io/badge/license-MIT-blue)](LICENSE)
[![](https://img.shields.io/badge/platform-Android-green)](https://developer.android.com)
[![](https://img.shields.io/badge/minSdk-21-yellow)](https://developer.android.com)

A sleek and customizable Android `View` that combines an animated pulsing effect with a loading spinner and success state. Great for form submission buttons or async actions.

---

## 🛠 Features

- Pulsing circle animation during loading
- Rotating spinner icon
- Optional success state icon
- Fully customizable via XML attributes or code
- Easy to integrate into any Android project

---

## 📦 Setup

### Project Structure

**settings.gradle**:
```groovy
include ':pulsingbutton'
```

**app/build.gradle**:
```groovy
implementation(project(":pulsingbutton"))
```
---

## 📷 Demo

<p align="Left">
  <img src="https://github.com/user-attachments/assets/a8f56098-06fb-4b92-adf9-81ceeaa916fd" width="200" height ="400" alt="Demo GIF"/>
</p>
  
---

## 📋 Usage

### XML

```xml
<com.daniel.pulsingbutton.PulsingLoadingButton
    android:id="@+id/pulsingButton"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:buttonText="Submit"
    app:pulseColor="@color/blue"
    app:successIcon="@drawable/ic_check_circle" 
    app:loadingIcon="@drawable/ic_spinner"   
    app:buttonColor="#6B5757"               
/>
```
> All of the attributes are optional since all have default values.

> If `app:loadingIcon` is omitted, a default `ic_spinner` will be used and rotated to simulate a spinner.

### Code

```java
PulsingLoadingButton button = findViewById(R.id.pulsingButton);

// Set state to loading (starts animation)
button.setState(PulsingLoadingButton.State.LOADING);

// After success
button.setState(PulsingLoadingButton.State.SUCCESS);

// Reset to idle
button.setState(PulsingLoadingButton.State.IDLE);
```

### Attributes

| Attribute       | Description                         | Default                   |
|-----------------|-------------------------------------|---------------------------|
| `buttonText`    | Text shown in idle state            | "Submit"                  |
| `pulseColor`    | Color of pulsing animation          | `#552196F3`               |
| `loadingIcon`   | Drawable to spin while loading      | `ic_spinner` (built-in)   |
| `successIcon`   | Drawable shown on success           | `checkbox_on_background`  |
| `buttonColor`   | Drawable shown on success           | `0xFF6200EE`              |

---

## 🌀 Spinner Icon

You may provide a custom drawable using `app:loadingIcon`. It should ideally be **asymmetrical**, such as a partial circle or arc, to make the rotation visible.

A default vector spinner icon is bundled as `ic_spinner.xml`:

```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="24"
    android:viewportHeight="24">
    <path
        android:fillColor="@android:color/white"
        android:pathData="M12,4a8,8 0,0 1,8 8h-2a6,6 0,0 0,-6 -6z"/>
</vector>
```

This icon is **rotated in code** to simulate loading. You can replace it with your own drawable.

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you’d like to change.

---

## 📄 License

MIT License. See [LICENSE](LICENSE) for details.


