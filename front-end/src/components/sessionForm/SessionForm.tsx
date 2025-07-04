import { useForm } from "react-hook-form";
import { craft, schema, sessionType, type SessionFormData } from "./schema";
import { zodResolver } from "@hookform/resolvers/zod";

interface SessionFormProps {
  onSubmit: (data: SessionFormData) => unknown;
  existingData?: SessionFormData;
  onCancel: () => void;
}

const SessionForm = ({
  onSubmit,
  existingData,
  onCancel,
}: SessionFormProps) => {
  const {
    handleSubmit,
    register,
    formState: { errors },
  } = useForm<SessionFormData>({
    resolver: zodResolver(schema),
    defaultValues: existingData || {
      sessionType: "TRAINING",
      craft: "DB20",
      date: new Date().toISOString().substring(0, 10),
      location: "Bank Street",
    },
  });

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      className="max-w-md mx-auto p-6 bg-white rounded-lg shadow-md"
    >
      <h2 className="text-2xl font-bold text-gray-800 mb-6">
        {existingData ? "EDIT SESSION" : "CREATE SESSION"}
      </h2>

      <section className="mb-4">
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Session Type
        </label>
        <select
          {...register("sessionType")}
          className={`w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-amber-500 ${
            errors.sessionType ? "border-red-500" : "border-gray-300"
          }`}
          defaultValue={existingData?.sessionType || ""}
        >
          <option value="" disabled>
            Select session type
          </option>
          {sessionType.map((type) => (
            <option key={type} value={type}>
              {type}
            </option>
          ))}
        </select>
        {errors?.sessionType && (
          <small className="text-red-500 text-xs mt-1 block">
            {errors?.sessionType?.message}
          </small>
        )}
      </section>
      <section className="mb-4">
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Craft
        </label>
        <select
          {...register("craft")}
          className={`w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-amber-500 ${
            errors.sessionType ? "border-red-500" : "border-gray-300"
          }`}
          defaultValue={existingData?.craft || "DB20"}
        >
          <option value="" disabled>
            Select craft
          </option>
          {craft.map((craft) => (
            <option key={craft} value={craft}>
              {craft}
            </option>
          ))}
        </select>
        {errors?.craft && (
          <small className="text-red-500 text-xs mt-1 block">
            {errors?.craft?.message}
          </small>
        )}
      </section>
      <section>
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Location
        </label>
        <input
          type="text"
          {...register("location")}
          className={`w-sm px-4 py-2 border rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-amber-500 ${
            errors.date ? "border-red-500" : "border-gray-300"
          }`}
        />
        {errors.location && (
          <small className="text-red-500 text-xs mt-1 block">
            {errors.location.message}
          </small>
        )}
      </section>

      <section>
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Session Date
        </label>
        <input
          type="date"
          {...register("date")}
          className={`w-sm px-4 py-2 border rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-amber-500 ${
            errors.date ? "border-red-500" : "border-gray-300"
          }`}
        />
        {errors.date && (
          <small className="text-red-500 text-xs mt-1 block">
            {errors.date.message}
          </small>
        )}
      </section>
      <section className="flex gap-4 justify-between pr-4">
        {/* Cancel Button */}
        <button
          type="button"
          onClick={onCancel}
          className="w-xs mt-6 bg-gray-200 rounded-lg hover:bg-gray-300 transition"
        >
          Cancel
        </button>
        {/* Submit Button */}
        <button
          type="submit"
          className="w-sm mt-6 bg-amber-500 hover:bg-amber-600 text-white font-medium py-2 px-4 rounded-lg transition duration-200"
        >
          {existingData ? "Edit Session" : "Create Session"}
        </button>
      </section>
    </form>
  );
};

export default SessionForm;

/**
 * 
 * 
 *  <select {...register("sessionType")}>
          {sessionType.map((type) => (
            <option key={type} value={type}>
              {type}
            </option>
          ))}
        </select>
 * 
 * 
 * 
 * <section>
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Session Type
        </label>
        <input
          type="text"
          {...register("sessionType")}
          className={`w-sm px-4 py-2 border rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-amber-500 ${
            errors.sessionType ? "border-red-500" : "border-gray-300"
          }`}
          placeholder="Enter session name"
        />
        {errors.sessionType && (
          <small className="text-red-500 text-xs mt-1 block">
            {errors.sessionType.message}
          </small>
        )}
      </section>
 */
