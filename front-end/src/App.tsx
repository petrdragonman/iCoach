import { BrowserRouter, Route, Routes } from "react-router";
import "./App.css";
import SessionsPage from "./pages/SessionsPage";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";
import SessionPage from "./pages/SessionPage";
import AttendancePage from "./pages/AttendancePage";

const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<SessionsPage />} />
          <Route path="/session" element={<SessionPage />} />
          <Route path="/attendance" element={<AttendancePage />} />
        </Routes>
      </BrowserRouter>
      <ReactQueryDevtools initialIsOpen={false} />
    </QueryClientProvider>
  );
}

export default App;
