import { useQuery } from "@tanstack/react-query";
import { getAllSessions } from "../services/SessionsService";
import SessionList from "../components/SessionList";

const SessionsPage = () => {
  const { data, isPending, error } = useQuery({
    queryKey: ["sessions"],
    queryFn: getAllSessions,
  });

  if (error) {
    alert("An error has accured while fetching data.");
  }

  return (
    <div>
      <header className="text-red-400 text-3xl">SESSIONS</header>
      {/* <div>{isPending ? <p>LOADING</p> : JSON.stringify(data)}</div> */}
      {!isPending && <SessionList sessions={data} />}
    </div>
  );
};

export default SessionsPage;
