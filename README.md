# fcu-systex-project-team2

逢甲大學與精誠資訊產學合作專題 (第二組) — 全端專案（Vue 3 + Vite 前端，Spring Boot 後端）

本檔為專案的初始化說明，包含建置、執行與常見錯誤排除步驟（針對 Windows / cmd.exe）。

---

## 專案結構（節錄）

- `backend/` — Spring Boot (Maven) 應用，主要程式在 `backend/src/main/java`，Maven 設定檔為 `backend/pom.xml`。
- `frontend/` — Vue 3 + Vite 前端專案，主要程式在 `frontend/src`，Node 設定與 script 在 `frontend/package.json`。

---

## 快速開始（Prerequisites）

- JDK 17+（專案 `pom.xml` 設定 `java.version` 為 21，但只要系統安裝合適的 JDK 並設定 `JAVA_HOME` 即可）
- Maven（建議安裝並在 PATH 中可呼叫 `mvn`）
- Node.js (請遵守 `frontend/package.json` 的 engines 範圍；專案要求 Node >= 20.x 或 >=22.12)
- （可選）IntelliJ IDEA：能自動匯入 Maven / Node 專案，方便開發

---

## 後端（Maven） - 建置與執行

1. 進入後端資料夾並檢查 Maven/Java 版本：

```cmd
cd backend
mvn -v
java -version
```

2. 下載相依並建置（若要強制更新遠端依賴）：

```cmd
mvn -U clean package
```

3. 直接啟動（開發）

```cmd
mvn spring-boot:run
```

4. 或執行已打包的 jar（build 後）：

```cmd
java -jar target\backend-0.0.1-SNAPSHOT.jar
```

注意：後端 `pom.xml` 使用了 Microsoft SQL Server 的 JDBC driver（`mssql-jdbc`），如要連線資料庫，請在 `backend/src/main/resources/application.properties` 或環境變數設定連線字串（JDBC URL、帳號、密碼）。

---

## 前端（Node / Vite） - 安裝與執行

1. 進入前端資料夾並安裝相依：

```cmd
cd frontend
npm install
```

2. 開發模式（熱重載）：

```cmd
npm run dev
```

3. 建置（production）：

```cmd
npm run build
```

4. 本地預覽建置結果：

```cmd
npm run preview
```

測試、型別檢查與 lint：

```cmd
npm run test:unit
npm run type-check
npm run lint
```

---

## 在 IntelliJ 中顯示 Maven 視窗 / 常見 UI 問題

- 若右側看不到 Maven 工具窗格：
  - View -> Tool Windows -> Maven
  - 或按 Ctrl+Shift+A，搜尋 `Maven` 並選 `View | Tool Windows | Maven`
- 如 `pom.xml` 沒被識別為 Maven 專案：在專案視窗對 `pom.xml` 右鍵，選 `Add as Maven Project` 或使用 `Import Maven Projects`。
- 若沒有 Maven 插件：File -> Settings -> Plugins，搜尋 `Maven` 或 `Maven Integration` 並啟用。
- 若 UI 行為怪異：Window -> Restore Default Layout，或 File -> Invalidate Caches / Restart。

---

## IntelliJ: 逐步快速匯入與執行（含命令）

下列步驟在 Windows / IntelliJ 中可直接跟著做，會涵蓋從開啟專案、設定 JDK/Node、匯入 Maven、執行後端與前端、以及常見問題的命令與修正方式。

前置：請先安裝好 JDK、Maven、Node（可用命令檢查）：

```cmd
# 檢查環境
echo %JAVA_HOME%
java -version
mvn -v
node -v
npm -v
```

步驟 A — 用 IntelliJ 開啟專案
1. File -> Open...
   - 選擇專案根目錄（例如：C:\...\fcu-systex-project-team2），按 OK。
   - 或直接選 `backend/pom.xml` 來只匯入後端 Maven 專案。
2. 若 IntelliJ 偵測到 `pom.xml`，會詢問是否匯入 Maven project，選「Import」或「Enable Auto-Import」。
3. 若沒有自動匯入：
   - View -> Tool Windows -> Maven 打開 Maven 視窗，點擊左上角的刷新圖示（Reload All Maven Projects）。

步驟 B — 設定 Project SDK / JDK
1. File -> Project Structure (或按 Ctrl+Alt+Shift+S)
2. 在 Project -> Project SDK 選擇已安裝的 JDK（若尚未列出，點 Add -> JDK 指向你的 JDK 安裝路徑，例如 `C:\Program Files\Java\jdk-21`）。
3. 同樣在 Modules -> 選取 module，確認 Language level 與 Project SDK 一致。

步驟 C — 匯入並檢查 Maven 依賴
1. 打開 Maven 工具窗格（View -> Tool Windows -> Maven）。
2. 在 Maven 視窗內，展開 `backend` 專案，點擊右上角的「刷新」圖示。
3. 若看到依賴無法下載或 parent POM 錯誤，打開底部 Terminal 並執行：

```cmd
cd backend
mvn -U clean package
```

將完整錯誤貼到 issue 或交給同學幫忙排查（常見是 proxy、settings.xml 或版本錯誤）。

步驟 D — 建立並執行 Spring Boot Run Configuration（兩種方式）
- 方式 1：使用 Run Configuration（推薦）
  1. Run -> Edit Configurations -> 點 + -> 選擇 `Spring Boot`（或 `Application` 若沒有 Spring option）。
  2. Name: backend (或任意)
  3. Main class: 點選 `...` 並選 `com.example.demo.DemoApplication`（或你的 main class）。
  4. Working directory: 專案 backend 路徑。
  5. Apply -> OK -> Run 按綠色三角。
- 方式 2：使用 Maven goal
  1. 在 Maven 工具窗格，展開 `Plugins` -> `spring-boot` -> 雙擊 `spring-boot:run`。
  2. 或使用 Terminal：

```cmd
cd backend
mvn spring-boot:run
```

步驟 E — 在 IntelliJ 啟動並監看前端（Vite）
1. 在 IntelliJ 中打開 `frontend/package.json`。如果要在 IDE 裡直接執行 npm script：
   - View -> Tool Windows -> NPM Scripts（或右側的 NPM 工具窗格），若沒看到，請在 Plugins 內啟用 `Node.js`/`NPM` 支援。
2. 先在 Terminal 執行一次安裝（或在 IntelliJ 的 Terminal）：

```cmd
cd frontend
npm install
```

3. 啟動開發伺服器：

```cmd
npm run dev
```

4. 若要在 IntelliJ 建立一個 npm run 配置：Run -> Edit Configurations -> + -> npm -> package.json path 指向 frontend\package.json，script 選 `dev`。

步驟 F — 設定 Node 解析度（若 IDE 無法執行 npm scripts）
1. File -> Settings -> Languages & Frameworks -> Node.js and NPM
2. 在 Node interpreter 選擇系統 Node（或添加新路徑）。
3. Apply -> OK。重新載入 package.json 視窗。

步驟 G — 常見問題與快速排查命令
- 若 Maven parent POM 找不到：
  - 檢查 `backend/pom.xml` 的 parent 版本（例如目前是 `3.5.6`），可到 https://search.maven.org 搜尋該版本是否存在。
  - 在 Terminal 執行：

```cmd
cd backend
mvn -X -U clean package > mvn-debug.txt
```
  - 將 `mvn-debug.txt` 內容提供給協助者（包含 stacktrace 與錯誤）。
- 若 IDE 無法展現 Maven 視窗或 plugin 功能：File -> Settings -> Plugins，搜尋並啟用 `Maven`、`Node.js`，重啟 IDE。
- 若更改後仍有怪異狀況：File -> Invalidate Caches / Restart

步驟 H — 進一步：設定 Auto-Import 與 Profiles
- 在 Maven 視窗右上齒輪按鈕 -> 勾選 `Always reimport`（或 `Import Maven projects automatically`），可省去手動刷新。
- 若使用不同環境的 Maven settings（proxy / mirror）：編輯 `%USERPROFILE%\.m2\settings.xml`，並在 IntelliJ 的 Maven 設定中確認指向正確的 settings.xml（File -> Settings -> Build, Execution, Deployment -> Build Tools -> Maven -> User settings file）。

---

## 常見錯誤與排除建議

1. 父 POM 無法解析（例：`org.springframework.boot:spring-boot-starter-parent:3.5.6`）：
   - 原因：指定的 Spring Boot parent 版本不存在、網路無法連到中央倉庫、公司 proxy 或 Maven `settings.xml` 配置問題。
   - 解法：
     - 檢查 `backend/pom.xml` 的 parent 版本是否為有效版本；若不確定，改為一個已釋出的版本（例如 3.1.x 或 3.2.x 分支的最新小版本），或到 https://search.maven.org 搜尋確認。
     - 在專案根或 `backend` 執行 `mvn -U clean package` 並觀察完整錯誤訊息。
     - 若公司內網需透過 proxy，設定 `%USERPROFILE%\.m2\settings.xml` 的 proxy 與私有 repository。

2. 下載依賴失敗（網路/證書/代理）：
   - 確認可連到 https://repo1.maven.org/ 或公司私服是否可用。
   - 短暫問題可重試（`mvn -U`），或在 `settings.xml` 加入 `<mirrors>` 指向內部 Nexus/Artifactory。

3. 前端執行錯誤（Node 版本不符）：
   - 安裝符合 `engines` 的 Node 版本（可使用 nvm 或 nvm-windows 管理多版本）。

---

## 建議的開發流程（本機）

1. 後端開發：在 `backend/` 使用 `mvn spring-boot:run` 開發（自動重啟可透過 devtools）。
2. 前端開發：在 `frontend/` 使用 `npm run dev`，Vite 會代理及熱重載。
3. 若前後端同時執行，建議在前端呼叫 API 時將 base URL 指向後端（或使用 Vite proxy 設定）。

---

## 測試與 CI

- 後端：`mvn test`
- 前端：`npm run test:unit`

---

## 簡短 FAQ

Q: 我在 IntelliJ 找不到 Maven 視窗。  
A: 確認你開啟的是包含 `backend/pom.xml` 的專案根目錄，然後 View -> Tool Windows -> Maven，或在 `pom.xml` 上右鍵 Add as Maven Project。

Q: 我看到 `Could not find artifact org.springframework.boot:spring-boot-starter-parent:3.5.6` 之類的錯誤。  
A: 這表示 parent POM 無法解析，請檢查 `backend/pom.xml` 的 spring-boot 版本是否正確，或依本 README 的常見錯誤段落排查網路/設定問題。

---

