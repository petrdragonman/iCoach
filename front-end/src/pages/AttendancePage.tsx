import { useLocation, useNavigate } from "react-router";
import { useAthletes } from "../services/athleteQueries";
import { toast } from "react-toastify";
import LoadingPlaceholder from "../components/loading placeholder/LoadingPlaceholder";
import AthleteList from "../components/AthleteList";

const AttendancePage = () => {
  const { data, isPending, error } = useAthletes();
  const navigate = useNavigate();
  const { state } = useLocation();

  const handleCancel = () => {
    navigate("/");
  };

  if (state) {
    //console.log(state);
  }

  if (error) {
    toast(error.message);
  }

  if (data) {
    //console.log(data);
  }

  return (
    <main className=" flex flex-col gap-0 justify-center items-center w-screen">
      <header className="text-red-400 text-3xl mt-20 mb-10">ATTENDANCE</header>
      {isPending && <LoadingPlaceholder />}
      {data && <AthleteList athletes={data} sessionId={state.id} />}
      <button
        onClick={handleCancel}
        className="flex justify-end text-3xl p-1 px-4 border border-orange-600 text-orange-600 mb-5 rounded-lg mt-10"
      >
        GO BACK
      </button>
    </main>
  );
};

export default AttendancePage;
