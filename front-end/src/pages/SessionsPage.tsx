import "react-toastify/dist/ReactToastify.css";
import { toast, ToastContainer } from "react-toastify";
import { useSessions } from "../services/queries";
import SessionList from "../components/SessionList";
import LoadingPlaceholder from "../components/loading placeholder/LoadingPlaceholder";
import { useNavigate } from "react-router";

const SessionsPage = () => {
  const { data, isPending, error, fetchStatus } = useSessions();
  const navigate = useNavigate();

  const handleCreateSession = () => {
    navigate("/session");
  };

  if (error) {
    toast(error.message);
  }

  return (
    <main className=" flex flex-col gap-0 justify-center items-center w-screen">
      <header className="text-red-400 text-3xl mt-20 mb-10">SESSIONS</header>
      <section className="flex w-100 justify-end-safe">
        <button
          onClick={handleCreateSession}
          className="flex justify-end text-3xl p-1 px-4 border border-orange-600 text-orange-600 mb-5 rounded-lg"
        >
          +
        </button>
      </section>

      <p>QUERY STATUS: {fetchStatus}</p>
      {isPending && <LoadingPlaceholder></LoadingPlaceholder>}
      {data && <SessionList sessions={data} />}
      <ToastContainer />
    </main>
  );
};

export default SessionsPage;
